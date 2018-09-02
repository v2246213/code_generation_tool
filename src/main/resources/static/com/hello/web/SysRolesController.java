package com.hello.web;




import com.magicbeans.base.Pages;
import com.magicbeans.base.ajax.ResponseData;
import com.magicbeans.framework.Message;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import com.magicbeans.base.kit.StrKit;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.validation.Valid;
import com.hello.service.ISysRolesService;
import com.hello.entity.SysRoles;
import BaseController

import org.springframework.stereotype.Controller;
import com.hello.framework.controller.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangnian123
 * @since 2018-09-02
 */
@Controller
@RequestMapping("/sysRoles")
public class SysRolesController extends BaseController {

    
    @Autowired
    private  ISysRolesService sysRolesService;


    /**
    * 跳转到列表页面
    */
    @GetMapping("list")
    public String list(Pages pages, Model model ){
        model.addAttribute("page", sysRolesService.findPage(pages,null,null));
        return "sysRoles/list";
    }

    /**
    * 跳转添加页面
    * @return
    */
    @GetMapping("add")
    public String toAdd(Model model){
        return "sysRoles/add";
    }


    /**
    * 编辑SysRoles
    * @param id
    * @param model
    * @return
    */
    @GetMapping("edit")
    public String edit(@RequestParam String id,Model model){
        model.addAttribute("sysroles",sysRolesService.find(id));
        return "sysRoles/edit";
    }

    /**
     * 根据ID刪除SysRoles
     *
     * @param id
     * @return
     */
    @GetMapping(value = "del/{id}")
    @ResponseBody
    public ResponseData del(@PathVariable String id) {
        ResponseData result = new ResponseData();
        sysRolesService.delete(id);
        return result;
    }

/**
    * 保存SysRoles
    *
    * @param sysroles
    * @param model
    * @return
    */
    @PostMapping(value = "save")
    public String save(@Valid SysRoles sysroles, Model model, RedirectAttributes redirectAttributes) {
        if (StrKit.isEmpty(sysroles.getId())) {
            sysRolesService.save(sysroles);
        } else {
            sysRolesService.update(sysroles);
        }

        addFlashMessage(redirectAttributes,new Message(Message.Type.success,"保存成功"));
        return redirect("list");
    }


}

