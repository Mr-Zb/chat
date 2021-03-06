package com.fun.api.service;

import com.alibaba.fastjson.JSON;
import com.fun.api.domain.FxFriends;
import com.fun.api.domain.FxRequestState;
import com.fun.api.domain.FxUserInfo;
import com.fun.api.domain.ImMessage;
import com.fun.api.mapper.FxFriendsMapper;
import com.fun.api.mapper.FxRequestStateMapper;
import com.fun.api.mapper.FxUserInfoMapper;
import com.fun.api.scoket.MyWebSocket;
import com.fun.framework.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FxFriendsService {

    @Autowired
    private FxFriendsMapper fxFriendsMapper;
    @Autowired
    private FxRequestStateMapper fxRequestStateMapper;
    @Autowired
    private FxUserInfoMapper userInfoMapper;
    @Autowired
    private  FxRequestStateMapper requestStateMapper;
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_friends
     *
     * @mbggenerated
     */
    public List<FxFriends> selectByUserId(Integer userId){
        return fxFriendsMapper.selectByUserId(userId);
    }
    public FxFriends selectByKeyword(String keyword){
        return fxFriendsMapper.selectByKeyword(keyword);
    }

    public int deleteByPrimaryKey(Integer fxId){
        return fxFriendsMapper.deleteByPrimaryKey(fxId);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_friends
     *
     * @mbggenerated
     */
    public int insert(FxFriends record){
        return fxFriendsMapper.insert(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_friends
     *
     * @mbggenerated
     */
    public int insertSelective(FxFriends record){
        return fxFriendsMapper.insertSelective(record);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = false)
    public int insertSelective(Integer userId,Integer state,Integer fxId,String requestMsg){
        FxRequestState fxRequestState = fxRequestStateMapper.selectByPrimaryKey(fxId);
        fxRequestState.setRequestId(fxRequestState.getRequestId());
        fxRequestState.setUserId(fxRequestState.getUserId());
        fxRequestState.setRequestState(state);
        fxRequestStateMapper.updateByIds(fxRequestState);
        FxFriends fxFriends = new FxFriends();
        fxFriends.setUserId(fxRequestState.getRequestId());
        fxFriends.setFriendId(fxRequestState.getUserId());
        fxFriendsMapper.insertSelective(fxFriends);
        FxFriends fxFriends1 = new FxFriends();
        fxFriends1.setUserId(fxRequestState.getUserId());
        fxFriends1.setFriendId(fxRequestState.getRequestId());
        fxFriends1.setFriendRemark(requestMsg);
        FxUserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        FxUserInfo friend = userInfoMapper.selectByPrimaryKey(fxRequestState.getRequestId());
        MyWebSocket myWebSocket = new MyWebSocket();
        ImMessage imMessage = new ImMessage();
        imMessage.setId(System.currentTimeMillis() + "");
        imMessage.setFrom_avatar(user.getAvatar());
        imMessage.setFrom_name(user.getNickName());
        imMessage.setFrom_id(userId);
        imMessage.setTo_id(fxId);
        imMessage.setType("system");
        //有备注显示备注没备注显示昵称
        imMessage.setTo_name(fxFriends.getNickName());
        imMessage.setTo_avatar(fxFriends.getAvatar());
        imMessage.setChat_type("user");
        imMessage.setData("你们已经是好友，可以开始聊天啦");
        imMessage.setOptions(null);
        imMessage.setCreate_time(System.currentTimeMillis());
        imMessage.setIs_remove(0);
        String message = JSON.toJSONString(imMessage);
        //
        myWebSocket.sendMessage(message, fxRequestState.getRequestId()+"", "user", userId + "", null);
        ImMessage imMessage2 = new ImMessage();
        imMessage2.setId(System.currentTimeMillis() + "");
        imMessage2.setFrom_id(fxRequestState.getRequestId());
        imMessage2.setFrom_name(StringUtils.isNotBlank(requestMsg)?requestMsg:friend.getNickName());
        imMessage2.setFrom_avatar(friend.getAvatar());
        imMessage2.setTo_id(userId);
        imMessage2.setTo_avatar(user.getAvatar());
        imMessage2.setTo_name(user.getNickName());
        imMessage2.setType("system");
        imMessage2.setChat_type("user");
        imMessage2.setData("你们已经是好友，可以开始聊天啦");
        imMessage2.setOptions(null);
        imMessage2.setCreate_time(System.currentTimeMillis());
        imMessage2.setIs_remove(0);
        //
        String message2 = JSON.toJSONString(imMessage2);
        myWebSocket.sendMessage(message2, userId+"", "user", fxRequestState.getRequestId() + "", null);
        return fxFriendsMapper.insertSelective(fxFriends1);
    }
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_friends
     *
     * @mbggenerated
     * @param fxId
     */
    public FxFriends selectByPrimaryKey(Integer fxId){
        return fxFriendsMapper.selectByPrimaryKey(fxId);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_friends
     *
     * @mbggenerated
     */
    public int updateByPrimaryKeySelective(FxFriends record){
        return fxFriendsMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fx_friends
     *
     * @mbggenerated
     */
    public int updateByPrimaryKey(FxFriends record){
        return fxFriendsMapper.updateByPrimaryKey(record);
    }

    public FxFriends selectFriend(Integer userId,Integer friendId){
        return fxFriendsMapper.selectFriend(userId,friendId);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class}, readOnly = false)
    public int deleteByIds(FxFriends record){
        requestStateMapper.deleteByIds(record.getUserId(),record.getFriendId());
        return fxFriendsMapper.deleteByIds(record);
    }

    public int updateByIds(FxFriends record){
        return fxFriendsMapper.updateByIds(record);
    }
}