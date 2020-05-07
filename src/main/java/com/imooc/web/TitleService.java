package com.imooc.web;

import com.imooc.dao.entity.FacultyDO;
import com.imooc.entity.Enum.SuccessEnum;
import com.imooc.entity.req.*;
import com.imooc.entity.resp.GetFacultyResp;
import com.imooc.entity.resp.GetStudentTitleResp;
import com.imooc.entity.resp.GetTeacherTitleResp;
import com.imooc.entity.resp.GetTitleListResp;
import com.imooc.manager.TitleServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangzhenqin on 2019/6/13.
 */
@RestController
@CrossOrigin
@RequestMapping("/title")
public class TitleService {
    @Autowired
    public TitleServiceManager titleServiceManager;

    @RequestMapping(value = "/getTitleList",method= RequestMethod.POST)
    public GetTitleListResp getTitleList(@RequestBody GetTitleListReq req){
        GetTitleListResp getTitleListResp =new GetTitleListResp();
        try{
            getTitleListResp =titleServiceManager.getTitleList(req);
            return getTitleListResp;
        }catch (Exception e){
            System.out.println("return:"+e);
            getTitleListResp.setRet(SuccessEnum.INNER_ERROR);
            return getTitleListResp;
        }
    }

    @RequestMapping(value = "/getTeacherTitle",method= RequestMethod.POST)
    public GetTeacherTitleResp getTeacherTitle(@RequestBody GetTeacherTitleReq req){
        GetTeacherTitleResp resp =new GetTeacherTitleResp();
        try{
            resp =titleServiceManager.getTeacherTitle(req);
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/updateTitleData",method= RequestMethod.POST)
    public SuccessEnum updateTitleData(@RequestBody UpdateTitleDataReq req){
        try{
            return titleServiceManager.updateTitleData(req);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/getTitleByDTO",method= RequestMethod.POST)
    public GetTitleListResp getTitleByDTO(@RequestBody GetTitleByDTOReq req){
        GetTitleListResp getTitleListResp =new GetTitleListResp();
        try{
            getTitleListResp =titleServiceManager.getTitleByDTO(req);
            return getTitleListResp;
        }catch (Exception e){
            System.out.println("return:"+e);
            getTitleListResp.setRet(SuccessEnum.INNER_ERROR);
            return getTitleListResp;
        }
    }

    @RequestMapping(value = "/delectTitle",method= RequestMethod.GET)
    public SuccessEnum delectTitle(Long titleId) {
        try {
            return titleServiceManager.delectTitle(titleId);
        } catch (Exception e) {
            System.out.println("return:" + e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/selectTitle",method= RequestMethod.GET)
    public SuccessEnum selectTitle(SelectTitleReq req) {
        try {
            return titleServiceManager.selectTitle(req);
        } catch (Exception e) {
            System.out.println("return:" + e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/cancelTitle",method= RequestMethod.GET)
    public SuccessEnum cancelTitle(CancelTitleReq req) {
        try {
            return titleServiceManager.cancelTitle(req);
        } catch (Exception e) {
            System.out.println("return:" + e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/getStudentTitle",method= RequestMethod.GET)
    public GetStudentTitleResp getStudentTitle(Long userId){
        GetStudentTitleResp resp =new GetStudentTitleResp();
        try{
            resp =titleServiceManager.getStudentTitle(userId);
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

    @RequestMapping(value = "/confirmStudentTitle",method= RequestMethod.GET)
    public SuccessEnum confirmStudentTitle(Long titleId){
        try{
            return titleServiceManager.confirmStudentTitle(titleId);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }

    @RequestMapping(value = "/cancelStudentTitle",method= RequestMethod.GET)
    public SuccessEnum cancelStudentTitle(Long titleId){
        try{
            return titleServiceManager.cancelStudentTitle(titleId);
        }catch (Exception e){
            System.out.println("return:"+e);
            return SuccessEnum.INNER_ERROR;
        }
    }
    @RequestMapping(value = "/getFaculty",method= RequestMethod.GET)
    public GetFacultyResp getFaculty(){
        GetFacultyResp resp =new GetFacultyResp();
        try{
            resp =titleServiceManager.getFaculty();
            return resp;
        }catch (Exception e){
            System.out.println("return:"+e);
            resp.setRet(SuccessEnum.INNER_ERROR);
            return resp;
        }
    }

//    @RequestMapping(value = "/addarea",method= RequestMethod.POST)
//    public Map<String,Object> addArea(@RequestBody Area area){
//        Map<String,Object> modelMap=new HashMap<String,Object>();
//        modelMap.put("success",areaService.addArea(area));
//        return modelMap;
//    }
//
}
