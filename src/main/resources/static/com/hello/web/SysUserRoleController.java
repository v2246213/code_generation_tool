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
import com.hello.service.ISysUserRoleService;
import com.hello.entity.SysUserRole;
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
@RequestMapping("/sysUserRole")
public class SysUserRoleController extends BaseController {

    
    @Autowired
    private  ISysUserRoleService sysUserRoleService;


    /**
    * 跳转到列表页面
    */
    @GetMapping("list")
    public String list(Pages pages, Model model ){
        model.addAttribute("page", sysUserRoleService.findPage(pages,null,null));
        return "sysUserRole/list";
    }

    /**
    * 跳转添加页面
    * @return
    */
    @GetMapping("add")
    public String toAdd(Model model){
        return "sysUserRole/add";
    }


    /**
    * 编辑SysUserRole
    * @param id
    * @param model
    * @return
    */
    @GetMapping("edit")
    public String edit(@RequestParam String id,Model model){
        model.addAttribute("sysuserrole",sysUserRoleService.find(id));
        return "sysUserRole/edit";
    }

    /**
     * 根据ID刪除SysUserRole
     *
     * @param id
     * @return
     */
    @GetMapping(value = "del/{id}")
    @ResponseBody
    public ResponseData del(@PathVariable String id) {
        ResponseData result = new ResponseData();
        sysUserRoleService.delete(id);
        return result;
    }

/**
    * 保存SysUserRole
    *
    * @param sysuserrole
    * @param model
    * @return
    */
    @PostMapping(value = "save")
    public String save(@Valid SysUserRole sysuserrole, Model model, RedirectAttributes redirectAttributes) {
        if (StrKit.isEmpty(sysuserrole.getId())) {
            sysUserRoleService.save(sysuserrole);
        } else {
            sysUserRoleService.update(sysuserrole);
        }

        addFlashMessage(redirectAttributes,new Message(Message.Type.success,"保存成功"));
        return redirect("list");
    }


}

