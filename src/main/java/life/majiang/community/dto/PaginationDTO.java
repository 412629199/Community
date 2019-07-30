package life.majiang.community.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象封装
 */
//加@data 可以自动提供getter and setter
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;//所有的问题
    private boolean showPre;//是否展示前一页
    private boolean showFirst;//是否展示首页
    private boolean showNext;//是否展示下一页
    private boolean showEnd;//是否展示最尾页
    private Integer page;//当前页
    private List<Integer>pages=new ArrayList<>();//所有的页
    private Integer totalPage;//总页数

    /**
     * 设置分页的方法
     * @param page 当前页数
     * @param totalCount 总页数
     * @param size 每页展示的数量
     */
    public void setPagination(Integer page,Integer totalCount,Integer size){

        //计算最大页数
        Integer totalPage=0;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        //设置页面页数容错
        if(page<1||page==null){
            //页数小于1 等时候也显示第一页
            page=1;
        }

        if(page>totalPage){
            //当页数大于最大页的时候显示最后一页
            page=totalPage;
        }

        this.page=page;
        //若当前为第一页 则不显示上一页
        if(page==1){
            showPre=false;
        }else {
            //否则显示上一页
            showPre=true;
        }

        pages.add(page);
        for (int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }

            if(page + i <=totalPage){
                pages.add(page + i);
            }
        }


        //若当前为最后一页 则不显示下一页
        if(page==totalPage){
            showNext=false;
        }else{
            //否则显示下一页
            showNext=true;
        }

        //判断是否展示首页
        if(pages.contains(1)){
            showFirst=false;
        }else{
            showFirst=false;
        }

        //判断是否展示尾页
        if(pages.contains(totalPage)){
            showEnd=false;
        }else{
            showEnd=true;
        }
    }
}
