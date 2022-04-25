package com.mySen.demo.controller;

import com.mySen.demo.model.Vote;
import com.mySen.demo.service.IsVoteservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("http://localhost:8081")
@RestController
public class VoteController {
    @Autowired
    private IsVoteservice isVoteservice;


    /**
     * @param
     * @Desc 创建投票接口
     * @return map
     */
    @PostMapping("api/Vote/addvote")
    public  String addVotelist(@RequestBody Vote vote){
        //将存储在数组中的选项持久化
        String[] arr = vote.getChildren();
        int len = arr.length;
        StringBuffer str = new StringBuffer();
        for(int i = 0;i<len-1;i++){
            str.append(arr[i]+"*");
        }
        str.append(arr[len-1]);
        vote.setOptions(str.toString());
        int i = isVoteservice.addVoteinfo(vote);
        return i>0? "success":"error";
    }

}
