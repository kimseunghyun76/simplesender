package com.ksh.mockup.repository;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class VuePage<T>  extends PageImpl<T> {

    private final long vuetotal;
    private final Pageable vuepageable;
    private String url;

    public VuePage(List<T> content, Pageable pageable, long total, String url){
        super(content, pageable, total);
        this.vuetotal = total;
        this.vuepageable = pageable;
        this.url = url;
    }

    @Override
    @JsonProperty("data")
    public List<T> getContent() {
        return super.getContent();
    }

    @Override
    @JsonProperty("current_page")
    public int getNumber() {
        return (super.getNumber() +1);
    }

    @Override
    @JsonProperty("last_page")
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @Override
    @JsonProperty("total")
    public long getTotalElements() {
        return vuetotal;
    }

    @Override
    @JsonProperty("per_page")
    public int getSize() {
        return super.getSize();
    }

    @JsonProperty("from")
    public int getFrom() {
        //현재 페이지  * 페이징 사이즈 + 1
        //null 처리 되어 있음
        int from = 0;
        from  = (super.getNumber() * super.getSize()) + 1;
        return from;
    }
    @JsonProperty("to")
    public int getTo() {
        //현재 페이지  * 페이징 사이즈 + 1
        //null 처리 되어 있음
        int from = 0;
        from  = ((super.getNumber()+ 1) * super.getSize()) ;
        return from;
    }


    @JsonProperty("next_page_url")
    public String getNext_page_url(){
        if(this.getTo()==this.getNumber()){
            return null;
        }
        return url+"?page=" + (getNumber() +1);
    }


    @JsonProperty("prev_page_url")
    public String getPrev_page_url(){
        if(this.getFrom()==this.getNumber()){
            return null;
        }
        return url+"?page=" + (getNumber() -1) ;
    }

}
