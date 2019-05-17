package com.chenyilei.cloud.discovery;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * --添加相关注释--
 *
 * @author chenyilei
 * @email 705029004@qq.com
 * @date 2019/05/14- 17:00
 */
@Qualifier
public class 工具test {
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {
        Date curWeek = new Date();
        Calendar instance = Calendar.getInstance();
        System.out.println(instance.getTime());
        instance.add(Calendar.MONTH,1);
        System.out.println(instance.getTime());

        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(new Date());
        instance2.get(Calendar.MONTH) ; instance.get(Calendar.MONTH);

//
//        Qualifier restTemplate = AnnotationUtils.findAnnotation(工具test.class, Qualifier.class);
//        System.out.println(restTemplate);
//
//        System.out.println("ParentController getAnnotation @RequestMapping: " + AnnotationUtils.getAnnotation(ParentController.class, RequestMapping.class));
//        System.out.println("ChildController getAnnotation @RequestMapping: " +AnnotationUtils.getAnnotation(ChildController.class, RequestMapping.class));
//        System.out.println();
//
//        System.out.println("ParentController findAnnotation @RequestMapping: " + AnnotationUtils.findAnnotation(ParentController.class, RequestMapping.class));
//        System.out.println("ChildController findAnnotation @RequestMapping: " + AnnotationUtils.findAnnotation(ChildController.class, RequestMapping.class));
//        System.out.println();
//
//        System.out.println("ParentController isAnnotated @RequestMapping: " + AnnotatedElementUtils.isAnnotated(ParentController.class, RequestMapping.class));
//        System.out.println("ParentController getMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.getMergedAnnotation(ParentController.class, RequestMapping.class));
//        System.out.println("ChildController isAnnotated @RequestMapping: " + AnnotatedElementUtils.isAnnotated(ChildController.class, RequestMapping.class));
//        System.out.println("ChildController getMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.getMergedAnnotation(ChildController.class, RequestMapping.class));
//        System.out.println();
//
//        System.out.println("ParentController hasAnnotation @RequestMapping: " + AnnotatedElementUtils.hasAnnotation(ParentController.class, RequestMapping.class));
//        System.out.println("ParentController findMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.findMergedAnnotation(ParentController.class, RequestMapping.class));
//        System.out.println("ChildController hasAnnotation @RequestMapping: " + AnnotatedElementUtils.hasAnnotation(ChildController.class, RequestMapping.class));
//        System.out.println("ChildController findMergedAnnotation @RequestMapping: " + AnnotatedElementUtils.findMergedAnnotation(ChildController.class, RequestMapping.class));
    }


    @RequestMapping(value = "parent/controller")
    class ParentController {
    }

    class ChildController extends ParentController {
    }
}
