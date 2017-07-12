package action;

import dao.DescriptionRepo;
import model.Description;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by admin on 2017/7/11.
 */
public class DescriptionAction extends BaseAction{
    @Autowired
    private DescriptionRepo descriptionRepo;
    private List<Description> descriptions;

    public void setDescriptionRepo(DescriptionRepo descriptionRepo) {
        this.descriptionRepo = descriptionRepo;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    @Action(value="/desc",results={@Result(type="json",name=SUCCESS)})
    public String execute(){
        Description d1=new Description();
        d1.setMore("more");
        Description d2=new Description();
        d2.setMore("even");
        descriptionRepo.save(d1);
        descriptionRepo.save(d2);
        descriptions=descriptionRepo.findAll();
        return SUCCESS;
    }
}
