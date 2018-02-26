package com.firesoon.drgs.controller.disease;

import com.firesoon.drgs.controller.base.BaseController;
import com.firesoon.drgs.dto.disease.Disease;
import com.firesoon.drgs.services.disease.DiseaseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 17:06
 */
@Api
@RestController
@RequestMapping(value = "/webservice/disease", method = RequestMethod.POST)
public class DiseaseController extends BaseController {

    @Autowired
    private DiseaseService service;

    @ApiOperation(value = "find", notes = "find")
    @RequestMapping(value = "find")
    public Object find(@RequestBody Disease disease) {
        PageHelper.startPage(disease.getPageNum(), disease.getPageSize());
        return success(new PageInfo<Disease>(service.find(disease)));
    }

    @ApiOperation(value = "import", notes = "import")
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public Object importFile(String filename, HttpServletRequest request) throws Exception {
    	String suffix = filename.substring(filename.lastIndexOf(".")+1);
        if(suffix != null && "CSV,XLSX,XLS,".contains(suffix.toUpperCase() + ",")){
        	service.importData(new File(request.getSession().getServletContext().getRealPath("/") + filename));
    	}else{
    		throw new RuntimeException("不支持的文件类型！");
    	}
        return success();
    }

    @ApiOperation(value = "upload", notes = "upload")
    @RequestMapping(value = "upload")
    public Object upload(MultipartFile file, HttpServletRequest request) throws Exception {
        if (file.isEmpty()) {
            return success("file can't be null!!!");
        }
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf(".")+1);
        if(suffix == null || !"CSV,XLSX,XLS,".contains(suffix.toUpperCase() + ",")){
        	throw new RuntimeException("不支持的文件类型！");
    	}
        String filepath = request.getSession().getServletContext().getRealPath("/") + filename;
        file.transferTo(new File(filepath));
        return success(filename);
    }

    @ApiOperation(value = "add", notes = "add")
    @RequestMapping(value = "add")
    public Object add(@RequestBody List<Disease> list) {
        return success(service.batchInsert(list));
    }

    @ApiOperation(value = "del", notes = "del")
    @RequestMapping(value = "del")
    public Object del(@RequestBody Disease disease) {
        service.delete(disease);
        return success();
    }

    @ApiOperation(value = "update", notes = "update")
    @RequestMapping(value = "update")
    public Object update(@RequestBody Disease disease) {
        service.update(disease);
        return success();
    }

    @ApiOperation(value = "启用/停用", notes = "启用/停用")
    @RequestMapping(value = "enable")
    public Object enable(@RequestBody Disease disease) {
        service.enable(disease);
        return success();
    }

    @ApiOperation(value = "findDisease", notes = "findDisease")
    @RequestMapping(value = "findDisease")
    public Object findDisease(@RequestBody Disease disease) {
        return success(service.findDisease(disease));
    }

    @ApiOperation(value = "findMedicare", notes = "findMedicare")
    @RequestMapping(value = "findMedicare")
    public Object findMedicare(@RequestBody Disease disease) {
        return success(service.findMedicare(disease));
    }


    @ApiOperation(value = "importProjectsettlement", notes = "import")
    @RequestMapping(value = "importProjectsettlement", method = RequestMethod.GET)
    public Object importProjectsettlement(String filename, HttpServletRequest request) throws Exception {
    	String suffix = filename.substring(filename.lastIndexOf(".")+1);
        if(suffix != null && "CSV,XLSX,XLS,".contains(suffix.toUpperCase() + ",")){
        	service.importProjectsettlement(new File(request.getSession().getServletContext().getRealPath("/") + filename));
    	}else{
    		throw new RuntimeException("不支持的文件类型！");
    	}
        return success();
    }




    @RequestMapping("getFilterData")
    public Object getFilterData(Map<String, Object> map){
        return succ(service.getFilterData(map));
    }
}