package me.dong.controller;

import me.dong.util.StringConverter;
import me.dong.model.service.vo.DateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DiController {

    @Autowired
    private StringConverter jsonStringConverter;

    @Autowired
    private StringConverter xmlStringConverter;

    @RequestMapping("/toJson")
    @ResponseBody
    public String toJsonString(){
        DateVO result = new DateVO();
        return jsonStringConverter.toString(result);
    }

    @RequestMapping("/toXml")
    @ResponseBody
    public String toXmlString(){
        DateVO result = new DateVO();
        return xmlStringConverter.toString(result);
    }


}
