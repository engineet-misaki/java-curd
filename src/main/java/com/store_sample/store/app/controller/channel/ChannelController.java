package com.store_sample.store.app.controller.channel;

import com.store_sample.store.app.controller.channel.dto.add_user.AddUserReq;
import com.store_sample.store.app.controller.channel.dto.add_user.AddUserRes;
import com.store_sample.store.app.controller.channel.dto.create.CreateChannelReq;
import com.store_sample.store.app.controller.channel.dto.create.CreateChannelRes;
import com.store_sample.store.app.controller.channel.dto.delete_user.DeleteUserReq;
import com.store_sample.store.app.controller.channel.dto.delete_user.DeleteUserRes;
import com.store_sample.store.app.controller.channel.dto.get.GetChannelRes;
import com.store_sample.store.app.controller.channel.dto.update.UpdateChannelReq;
import com.store_sample.store.app.controller.channel.dto.update.UpdateChannelRes;
import com.store_sample.store.domain.channels.model.FindAllChannelModel;
import com.store_sample.store.domain.channels.model.FindIdChannelModel;
import com.store_sample.store.domain.channels.service.ChannelDomainService;
import com.store_sample.store.infrastructure.jpa.channels.TblChannels;
import com.store_sample.store.service.channel.ChannelService;
import com.store_sample.store.service.channel.command.ChannelAddedUserCommand;
import com.store_sample.store.service.channel.command.ChannelDeletedUserCommand;
import com.store_sample.store.service.channel.command.CreateChannelCommand;
import com.store_sample.store.service.channel.command.UpdateChannelCommand;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/channels")
public class ChannelController {

  private final ChannelService channelService;
  private final ChannelDomainService channelDomainService;

  @GetMapping
  public List<FindAllChannelModel> findAll() {
    return channelService.findAll();
  }

  @PostMapping
  public CreateChannelRes create(@RequestBody CreateChannelReq request) {
    CreateChannelCommand command = new CreateChannelCommand();
    command.setName(request.getName());
    TblChannels entity = channelService.create(command);

    CreateChannelRes res = new CreateChannelRes();
    res.setId(entity.getId());
    res.setName(entity.getName());

    return res;
  }

  @PutMapping("/{id}")
  public UpdateChannelRes update(@PathVariable("id") int id,
      @RequestBody UpdateChannelReq request) {
    UpdateChannelCommand command = new UpdateChannelCommand();
    command.setId(id);
    command.setName(request.getName());

    TblChannels entity = channelService.update(command);

    UpdateChannelRes res = new UpdateChannelRes();
    res.setId(entity.getId());
    res.setName(entity.getName());

    return res;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable("id") int id) {

    channelService.delete(id);
  }

  @GetMapping("/{id}")
  public GetChannelRes findById(@PathVariable("id") int id) {

    FindIdChannelModel model = channelService.findById(id);
    GetChannelRes res = new GetChannelRes();
    res.setId(model.getId());
    res.setName(model.getName());
    res.setMembers(model.getChannelMembers());
    res.setMessages(model.getMessages());
    return res;
  }

  @PostMapping("/{id}/add-user")
  public AddUserRes addUser(@PathVariable("id") int id, @RequestBody AddUserReq request) {
    ChannelAddedUserCommand command = new ChannelAddedUserCommand();
    command.setChannelId(id);
    command.setUserIds(request.getUserIds());
    channelService.channelAddedUser(command);

    AddUserRes res = new AddUserRes();
    res.setMsg("成功");
    return res;
  }

  @PostMapping("/{id}/delete-user")
  public DeleteUserRes deleteUser(@PathVariable("id") int id, @RequestBody DeleteUserReq request) {
    ChannelDeletedUserCommand command = new ChannelDeletedUserCommand();
    command.setChannelId(id);
    command.setUserIds(request.getUserIds());
    channelService.channelDeletedUser(command);

    DeleteUserRes res = new DeleteUserRes();
    res.setMsg("成功");
    return res;
  }
}
