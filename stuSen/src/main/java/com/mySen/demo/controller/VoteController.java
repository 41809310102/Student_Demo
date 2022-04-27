package com.mySen.demo.controller;

import com.mySen.demo.dao.VoteMapper;
import com.mySen.demo.model.Action;
import com.mySen.demo.model.Vote;
import com.mySen.demo.model.Votedatas;
import com.mySen.demo.service.IsVoteservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin("http://localhost:8080")
@RestController
public class VoteController {
    @Autowired
    private IsVoteservice isVoteservice;
    @Autowired
    private VoteMapper voteMapper;
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


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
            str.append(arr[i]+"-");
        }
        str.append(arr[len-1]);
        vote.setOptions(str.toString());
        int i = isVoteservice.addVoteinfo(vote);
        return i>0? "success":"error";
    }


    /**
     * @param
     * @Desc 获取投票接口
     * @return map
     */
    @GetMapping("api/Vote/selectvote")
    public Map<String,Object> selectVotelist(@RequestParam int id){
        //将存储在数组中的选项持久化
        Map<String,Object> map = new HashMap<>();
        try{
            List<Vote> sub  = isVoteservice.selectVoteinfo(id);
            int numbers = sub.size();
            List<Votedatas> res = new LinkedList<>();
            if(numbers<=0){
                map.put("code",1);
                map.put("msg","当前没有相关投票信息");
                return map;
            }

            for(Vote vote : sub){
              //判断是否过期
                String str = sdf.format(new Date());
                String a = str.replace("-","");
                String b = vote.getEndtime().substring(0,9).replace("-","");
                int nows = Integer.parseInt(a);
                int votetime = Integer.parseInt(b);
                if(votetime<nows){
                    vote.setIsout(false);
                }else{
                    vote.setIsout(true);
                }
                vote.setChildren(vote.getOptions().split("-"));
                for(int i = 0;i<vote.getChildren().length;i++){
                    Votedatas node = new Votedatas();
                    node.setId(i+1);node.setTitle(vote.getChildren()[i]);node.setVid(vote.getId());
                    //添加标记token
                    StringBuffer token = new StringBuffer();
                    token.append(vote.getId());
                    node.setToken(token.toString());
                    if(i>=0&&i==0){
                        node.setNum(vote.getA());
                    }else if(i>=1&&i==1){
                        node.setNum(vote.getB());
                    }else if(i>=2&&i==2){
                        node.setNum(vote.getC());
                    }else if(i>=3&&i==3){
                        node.setNum(vote.getD());
                    }
                    res.add(node);
                }
            }
            map.put("data",res);
            map.put("code",1);
            map.put("numbers",numbers);
            map.put("msg","数据获取成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器发生错误!");
        }
        return map;
    }


    /**
     * @param
     * @Desc 获取全部没有投票的投票接口
     * @return map
     */
    @GetMapping("api/Vote/selectvoteall")
    public Map<String,Object> selectVotelistall(@RequestParam String  major,
                                                @RequestParam int uid){
        //将存储在数组中的选项持久化
        Map<String,Object> map = new HashMap<>();
        try{
            List<Vote> sub  = isVoteservice.selectVoteinfoall(major,uid);
            int numbers = sub.size();
            map.put("data",sub);
            map.put("code",1);
            map.put("numbers",numbers);
            map.put("msg","数据获取成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器发生错误!");
        }
        return map;
    }



    /**
     * @param
     * @Desc 获取全部已经投了票的投票接口
     * @return map
     */
    @GetMapping("api/Vote/selectvotelog")
    public Map<String,Object> selectVotelistlog(@RequestParam String  major,
                                                @RequestParam int uid){
        //将存储在数组中的选项持久化
        Map<String,Object> map = new HashMap<>();
        try{
            List<Vote> sub  = isVoteservice.selectVoteinfolog(major,uid);
            int numbers = sub.size();
            map.put("data",sub);
            map.put("code",1);
            map.put("numbers",numbers);
            map.put("msg","数据获取成功!");
        }catch (Exception e){
            System.out.println(e.toString());
            map.put("code",-1);
            map.put("msg","服务器发生错误!");
        }
        return map;
    }

    /**
     * @param
     * @Desc 获取投票接口
     * @return map
     */
    @GetMapping("api/Vote/getMyoption")
     public String getaddMyoption(@RequestParam int vid,@RequestParam int option,@RequestParam int uid){
         int i = 0;
         //判断用户选择
         if(option==1){
            i = isVoteservice.addA(vid);
         }else if(option==2){
             i = isVoteservice.addB(vid);
         }else if(option==3){
             i = isVoteservice.addC(vid);
         }else{
             i = isVoteservice.addD(vid);
         }

          i =i+ voteMapper.insertvotelog(vid,uid);

         return i>1? "success":"error";
     }
}
