package com.example.app_supportpolywork.data.manager;

import com.example.app_supportpolywork.R;
import com.example.app_supportpolywork.data.model.cv_model.CV;

import java.util.ArrayList;
import java.util.List;

public class CVTemplateManager {

    public static List<CV> getCVTemplate() {
        List<CV> cvList = new ArrayList<>();

        cvList.add(new CV(0, R.layout.cv_template_1, R.drawable.cv_template_1, "CV Hiện Đại 1", CV.CVType.FREE));
        cvList.add(new CV(1, R.layout.cv_template_1, R.drawable.cv_template_2, "CV Hiện Đại 2", CV.CVType.FREE));
        cvList.add(new CV(2, R.layout.cv_template_1, R.drawable.cv_template_3, "CV Cơ Bản 1", CV.CVType.FREE));
        cvList.add(new CV(3, R.layout.cv_template_1, R.drawable.cv_template_4, "CV Cơ Bản 2", CV.CVType.FREE));
        cvList.add(new CV(4, R.layout.cv_template_1, R.drawable.cv_template_5, "CV Tinh Tế", CV.CVType.FREE));
        cvList.add(new CV(5, R.layout.cv_template_1, R.drawable.cv_template_6, "CV Màu Sắc", CV.CVType.FREE));
        cvList.add(new CV(6, R.layout.cv_template_1, R.drawable.cv_template_1, "CV Hiện Đại 3", CV.CVType.FREE));
        cvList.add(new CV(7, R.layout.cv_template_1, R.drawable.cv_template_2, "CV Hiện Đại 4", CV.CVType.PREMIUM));
        cvList.add(new CV(8, R.layout.cv_template_1, R.drawable.cv_template_3, "CV Hiện Đại 5", CV.CVType.PREMIUM));
        cvList.add(new CV(9, R.layout.cv_template_1, R.drawable.cv_template_4, "CV Hiện Đại 6", CV.CVType.PREMIUM));
        cvList.add(new CV(10, R.layout.cv_template_1, R.drawable.cv_template_5, "CV Hiện Đại 7", CV.CVType.PREMIUM));

        return cvList;
    }
}
