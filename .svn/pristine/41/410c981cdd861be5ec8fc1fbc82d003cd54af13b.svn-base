package com.firesoon.drgs.controller.range;

import com.firesoon.drgs.controller.base.BaseController;
import com.firesoon.drgs.dto.range.Range;
import com.firesoon.drgs.services.range.RangeService;
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
/**
 * @author create by yingjie.chen on 2017/10/11.
 * @version 2017/10/11 15:05
 */

@RestController
@Api
@RequestMapping(value = "/webservice/range/", method = RequestMethod.POST)
public class RangeController extends BaseController {


    @Autowired
    private RangeService service;

    @ApiOperation(value = "find", notes = "find")
    @RequestMapping(value = "find")
    public Object find(@RequestBody Range range){
        PageHelper.startPage(range.getPageNum(), range.getPageSize());
        return success(new PageInfo<Range>(service.findRanges(range)));
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
        if(file.isEmpty()){
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

    @ApiOperation(value = "del", notes = "del")
    @RequestMapping(value = "del")
    public Object del(@RequestBody Range range){
        String[] ids = range.getIds().split(",");
        for(String id : ids){
            service.delete(Integer.parseInt(id));
        }
        return success();
    }

    @ApiOperation(value = "update", notes = "update")
    @RequestMapping(value = "update")
    public Object update(@RequestBody Range range){
        service.update(range);
        return success();
    }

    @ApiOperation(value = "add", notes = "add")
    @RequestMapping(value = "add")
    public Object add(@RequestBody List<Range> ranges){
        return success(service.batchInsert(ranges));
    }

}
