package com.ksh.mockup.service;

import com.ksh.mockup.entity.MyFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class FileService {

    private static final Logger log = LoggerFactory.getLogger(FileService.class);

    public List<String> getSVList(){
        List<String> svList = new ArrayList<>();
        try{
            ClassLoader cl = this.getClass().getClassLoader();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
            Resource[] resources = resolver.getResources("classpath:static/svlist/**/*.*") ;
            for (Resource resource: resources){
                svList.add(resource.getFilename());
                log.warn(resource.getFilename());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return svList;
    }

    public String getSVFile(MyFile mcSvFile){
        StringBuilder result = new StringBuilder("");
        if(mcSvFile.getFileName() != null && mcSvFile.getFileName() != "" ){
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("static/svlist/"+mcSvFile.getFileName()).getFile());
            if(file.isFile()){
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        result.append(line).append("\n");
                    }
                    scanner.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                result.append("No files");
            }
        }else{
            result.append("No File Name");
        }
        return result.toString();
    }

    public String getJsonFile(MyFile mcSvFile){
        StringBuilder result = new StringBuilder("");
        if(mcSvFile.getFileName() != null && mcSvFile.getFileName() != "" ){
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("static/json/"+mcSvFile.getFileName()).getFile());
            if(file.isFile()){
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        result.append(line).append("\n");
                    }
                    scanner.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                result.append("No files");
            }
        }else{
            result.append("No File Name");
        }
        return result.toString();
    }
}
