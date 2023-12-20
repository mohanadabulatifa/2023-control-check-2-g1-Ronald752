package org.springframework.samples.petclinic;

import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.samples.petclinic.campaignDesign.CampaignDesignTest;
import org.springframework.samples.petclinic.disease.campaign.CampaignDesignAlgorithm;
import org.springframework.samples.petclinic.disease.campaign.DummyCampaignDesignAlgorithm;
import org.springframework.samples.petclinic.disease.campaign.ValidCampaingDesignAlgorithm;
import org.springframework.samples.petclinic.disease.campaign.WrongCampaignDesignAlgorithm;

import junit.framework.AssertionFailedError;

public class Test9 extends ReflexiveTest{    

    @ParameterizedTest    
    @MethodSource("provideAlgorithmsAndExpectedResults")
    public void testCampaignDesignAlgorithm(CampaignDesignAlgorithm alg, boolean shouldFail){
        // Configure SUT:
        CampaignDesignTest cdaTest=new CampaignDesignTest();
        cdaTest.setAlgorithm(alg);
        int numberOfExecutedTestMethods=0;
        // ExecuteTests        
        numberOfExecutedTestMethods=executeTests(cdaTest, shouldFail);             
        if(numberOfExecutedTestMethods<1)  
            fail("You have not specified any test method!");    
    }

    private void executeAfterEach(CampaignDesignTest cdaTest) {
        Method[] methods=cdaTest.getClass().getDeclaredMethods();
        for(Method method:methods){
            if(isMethodAnnotatedWithAfterEach(method)){
                try {                    
                    method.invoke(cdaTest);                    
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error while trying to invoke method:"+method.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    private int executeTests(CampaignDesignTest cdaTest, boolean shouldFail) {
        int executed=0;
        Method[] methods=cdaTest.getClass().getDeclaredMethods();
        boolean failDetected=false;
        String message="No test method detected the faulty implementation of the algorithm";
        for(Method method:methods){
            if(isMethodAnnotatedWithTest(method)){
                try {                                        
                    executed++;
                    executeBeforeEach(cdaTest);
                    method.invoke(cdaTest);     
                    executeAfterEach(cdaTest);
                }catch(AssertionError assertionError){
                    failDetected=true;
                    message="The test method named "+method.getName()+" failed (and should not)! AsssertionError: "+assertionError.getMessage();
                } catch(InvocationTargetException e){
                    if(e.getTargetException() instanceof org.opentest4j.AssertionFailedError){
                        failDetected=true;
                        message="The test method named "+method.getName()+" failed (and should not)! AsssertionError: "
                                    +((org.opentest4j.AssertionFailedError)e.getTargetException()).getMessage();
                    }else
                        System.out.println("Error while trying to invoke method:"+method.getName());                    
                }catch (IllegalAccessException | IllegalArgumentException  e) {                    
                    System.out.println("Error while trying to invoke method:"+method.getName());                    
                }
            }
        }
        if(failDetected!=shouldFail)
            fail(message);
        return executed;
    }

    private void executeBeforeEach(CampaignDesignTest cdaTest) {
        Method[] methods=cdaTest.getClass().getDeclaredMethods();
        for(Method method:methods){
            if(isMethodAnnotatedWithBeforeEach(method)){
                try {
                    method.invoke(cdaTest);
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    System.out.println("Error while trying to invoke method:"+method.getName());
                    e.printStackTrace();
                }
            }
        }
    }    

    public static Stream<Arguments> provideAlgorithmsAndExpectedResults(){
        return Stream.of(
            Arguments.of(new ValidCampaingDesignAlgorithm(), false),
            Arguments.of(new DummyCampaignDesignAlgorithm(), true),
            Arguments.of(new WrongCampaignDesignAlgorithm(), true)
        );
    }
}
