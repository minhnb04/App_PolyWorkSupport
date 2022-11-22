package com.example.app_supportpolywork.data.dummy;

import com.example.app_supportpolywork.data.model.Gender;
import com.example.app_supportpolywork.data.model.Job;
import com.example.app_supportpolywork.data.model.Position;
import com.example.app_supportpolywork.data.model.Technology;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JobDummy {
    private static final Random random = new Random();
    private static final List<String> titles = new ArrayList<>();
    private static final List<String> workplaces = new ArrayList<>();
    private static final List<Job.WorkForm> workForm = new ArrayList<>();
    private static final List<Gender> genders = new ArrayList<>();
    private static final List<Position> positions = new ArrayList<>();
    private static final List<Technology> technologies = new ArrayList<>();

    static {
        titles.add("Giảng Viên Java - Chuyên Viên Đào Tạo CNTT/Lập Trình");
        titles.add("Chuyên Viên Phát Triển Phần Mềm");
        titles.add("Trưởng Nhóm IT System (Lương Cứng 18,000,000 - 25,000,000)");
        titles.add("Chuyên Viên Phát Triển Website");
        titles.add("Java Developer - Lương Từ 15 - 30 Triệu ");
        titles.add("IT Project (Sharepoint Project And Helpdesk Project) - Fulltime Remote");
        titles.add("PHP Developer (PHP, Laravel, Aws)");
        titles.add("Kỹ Sư Phần Mềm Nhúng / Embedded Linux Software Engineer");
        titles.add("Automation Tester (Upto $1000)");

        workplaces.add("Hà Nội");
        workplaces.add("Hồ Chí Minh");
        workplaces.add("Vinh, Nghệ An");
        workplaces.add("Đà Nẵng");

        workForm.add(Job.WorkForm.REMOTE);
        workForm.add(Job.WorkForm.FULL_TIME);
        workForm.add(Job.WorkForm.PART_TIME);

        genders.add(Gender.MALE);
        genders.add(Gender.FEMALE);
        genders.add(Gender.NONE);

        positions.add(Position.INTERN);
        positions.add(Position.FRESHER);
        positions.add(Position.JUNIOR);
        positions.add(Position.DEV);
        positions.add(Position.SENIOR);
        positions.add(Position.MANAGER);
        positions.add(Position.OTHER);

        technologies.add(Technology.ANDROID);
        technologies.add(Technology.ANDROID_KOTLIN);
        technologies.add(Technology.JAVA);
        technologies.add(Technology.NODE_JS);
        technologies.add(Technology.PHP);
        technologies.add(Technology.OTHER);
        technologies.add(Technology.IOS);
    }

    private static String getRandomTitle() {
        return titles.get(random.nextInt(titles.size()));
    }

    private static String getRandomWorkPlace() {
        return workplaces.get(random.nextInt(workplaces.size()));
    }

    private static String getRandomWorkForm() {
        return workForm.get(random.nextInt(workForm.size())).value;
    }

    private static String getRandomGender() {
        return genders.get(random.nextInt(genders.size())).value;
    }

    private static String getRandomPosition() {
        return positions.get(random.nextInt(positions.size())).value;
    }

    private static String getRandomTechnology() {
        return technologies.get(random.nextInt(technologies.size())).value;
    }

    public static List<Job> getJobList() {
        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Job job = new Job();
            job.setAvatar(null);
            job.setId(i + "");
            job.setTitle(getRandomTitle());
            job.setStartSalary(1_000_000 * (random.nextInt(10) + 1));
            job.setEndSalary(1_000_000 * (random.nextInt(50) + 10));
            job.setWorkPlace(getRandomWorkPlace());
            job.setWorkForm(getRandomWorkForm());
            job.setStartNeededNumberOfPeople(random.nextInt(5) + 1);
            job.setEndNeededNumberOfPeople(random.nextInt(20) + 5);
            job.setGender(getRandomGender());
            job.setPosition(getRandomPosition());
            job.setExperience("1 năm kinh nghiệm");
            job.setDescription("Lĩnh vực: Báo Thể Thao - Chuyên tin tức bóng đá, cung cấp data lịch thi đấu, tỷ số trực tuyến, bảng xếp hạng...\n" +
                    "\n" +
                    "Công ty thành lập dự án mới nên cần lập 1 SEO Team, ưu tiên SEO Leader chọn địa điểm mở văn phòng mới để thuận tiện làm việc.\n" +
                    "\n" +
                    "Chịu trách nhiệm tổng thể cho các dự án SEO\n" +
                    "Cùng BGĐ tuyển dụng các vị trí còn lại như Content và nhân viên SEO\n" +
                    "Tìm hiểu, nghiên cứu, phân tích xu hướng thị trường, hiểu và nắm được các sản phẩm/dịch vụ của Công ty cũng như đối thủ để từ đó xây dựng và lập kế hoạch và phát triển hệ thống SEO hiệu quả\n" +
                    "Quản lý chi phí của chiến dịch dựa vào ngân sách và dự tính chi phí hàng tháng của phòng SEO\n" +
                    "Nắm bắt và cập nhật kịp thời xu hướng mới nhất và áp dụng vào SEO\n" +
                    "Quản lý, phân công, giám sát, đào tạo đội ngũ phòng SEO. Team có 3-4 nhân sự chuyên làm SEO.\n" +
                    "Thực hiện báo cáo phân tích, tổng hợp kết quả SEO, đề xuất các giải pháp, chiến lược SEO mới, nâng cao hiệu quả công việc.\n" +
                    "Lập kế hoạch triển khai dự án SEO.\n" +
                    "Thực hiện các công việc khác theo sự phân công của cấp trên.\n" +
                    "Làm việc từ T2 – T7 (T7 làm nửa ngày, nghỉ Chủ Nhật)");

            job.setBenefits("Lương 20 - 30tr tùy năng lực (thu nhập tối thiểu từ 50tr/tháng trở lên)\n" +
                    "Thưởng KPI khi đạt thành tích và thưởng duy trì hàng tháng khi giữ được thành tích\n" +
                    "Thưởng sinh nhật, thưởng Tết tối thiểu 2 tháng lương - tối đa không giới hạn\n" +
                    "Văn phòng rộng rãi, trang thiết bị hiện đại, hỗ trợ chi phí ăn trưa và nước uống hàng ngày\n" +
                    "Review xét duyệt tăng lương theo quý, năm.\n" +
                    "Nghỉ phép 12 ngày/năm\n" +
                    "Được hướng dẫn, đào tạo để phát triển nghề nghiệp lâu dài.\n" +
                    "Tham gia các chương trình training, team building, du lịch cùng công ty 2 lần/năm (trong nước & nước ngoài)");
            job.setRequirement("Có kinh nghiệm làm việc ở vị trí tương đương tối thiểu 2 năm.\n" +
                    "Giao tiếp tốt\n" +
                    "Kỹ năng phân tích và tổng hợp\n" +
                    "Kỹ năng quản lý nhóm\n" +
                    "Đã có những dự án thành công lớn, có kinh nghiệm trong ngành thể thao… là lợi thế.\n" +
                    "Ứng viên đã từng làm việc tại vị trí tương đương tại các Agency lớn là một lợi thế");
            job.setTechnology(getRandomTechnology());
            job.setExpiryApply("16:30 22/11/2022");
            jobs.add(job);
        }
        return jobs;
    }


}
