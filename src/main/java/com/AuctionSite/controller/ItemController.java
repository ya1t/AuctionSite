package com.AuctionSite.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AuctionSite.model.Item;
import com.AuctionSite.repository.ItemRepository;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public ItemController(ItemRepository itemRepository, SimpMessagingTemplate messagingTemplate) {
        this.itemRepository = itemRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping("/items")
    public String itemList(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "itemList";  // itemList.jsp로 이동
    }

    @GetMapping("/items/add")
    public String showAddItemForm() {
        return "itemForm";  // itemForm.jsp로 이동
    }

    @Transactional
    @PostMapping("/items/add")
    public String addItem(
            @RequestParam String name,
            @RequestParam MultipartFile image,
            @RequestParam int maxParticipants,
            RedirectAttributes redirectAttributes) throws IOException {

        if (image.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "이미지 파일이 비어 있습니다.");
            return "redirect:/items/add";
        }

        String imageName = image.getOriginalFilename();
        String imagePath = "src/main/resources/static/images/" + imageName;
        Path path = Paths.get(imagePath);

        Files.createDirectories(path.getParent());
        Files.write(path, image.getBytes());

        Item item = new Item();
        item.setName(name);
        item.setImagePath("/images/" + imageName);
        item.setMaxParticipants(maxParticipants);

        itemRepository.save(item);
        redirectAttributes.addFlashAttribute("successMessage", "아이템이 성공적으로 추가되었습니다!");
        return "redirect:/items";
    }

    @PostMapping("/items/participate")
    public String participate(
            @RequestParam Long itemId,
            @RequestParam String username,
            RedirectAttributes redirectAttributes) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid item ID"));

        // 참가자 추가 시도
        if (item.addParticipant(username)) {
            itemRepository.save(item);

            if (item.isFull()) {
                // 채팅방 참가자들에게만 메시지 전송
                for (String participant : item.getParticipants()) {
                    messagingTemplate.convertAndSendToUser(
                        participant, "/topic/room", String.valueOf(itemId)
                    );
                }
            } else {
                messagingTemplate.convertAndSend("/topic/items", "Item updated");
            }
            return "redirect:/items";
        } else {
            redirectAttributes.addFlashAttribute("error", "참가 인원이 초과되었습니다.");
            return "redirect:/items";
        }
    }
}
