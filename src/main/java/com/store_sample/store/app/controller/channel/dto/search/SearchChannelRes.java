package com.store_sample.store.app.controller.channel.dto.search;

import com.store_sample.store.app.controller.channel.dto.Channel;
import java.util.List;
import lombok.Data;

@Data
public class SearchChannelRes {

  List<Channel> channelList;
}
