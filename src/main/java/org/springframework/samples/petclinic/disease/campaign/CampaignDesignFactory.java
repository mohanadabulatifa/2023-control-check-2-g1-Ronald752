package org.springframework.samples.petclinic.disease.campaign;

public class CampaignDesignFactory {
    public static CampaignDesignAlgorithm create(String name){
        CampaignDesignAlgorithm result=null;
        switch(name){
            case "ValidCampaingDesignAlgorithm":
                result=new ValidCampaingDesignAlgorithm();
                break;
            case "DummyCampaignDesignAlgorithm":
                result=new DummyCampaignDesignAlgorithm();
                break;
            case "WrongCampaignDesignAlgorithm":
                result=new WrongCampaignDesignAlgorithm();
                break;
            default:
                result=new ValidCampaingDesignAlgorithm();
        }
        return result;
    }
}
