package com.store_sample.store.app.controller;

import com.store_sample.store.app.service.ChannelService;
import com.store_sample.store.domain.channels.model.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
@CrossOrigin
public class ChannelController {

    private final ChannelService channelService;

    @GetMapping
    public List<Channel> findAll () {
        return channelService.findAll();
    }

    @PostMapping
    public Channel create (@RequestBody Channel channel) {
        return channelService.create(channel);
    }

    @PutMapping("/{id}")
    public Channel update (@PathVariable("id") int id, @RequestBody Channel channel) {
        channel.setId(id);

        return channelService.update(channel);
    }
}
