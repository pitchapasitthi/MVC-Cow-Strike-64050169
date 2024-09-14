import java.util.Random;

public class CowModel {
    private String id;      // รหัสของวัว 8 หลัก
    private String breed;   // พันธุ์ของวัวตามสี (ขาวหรือน้ำตาล)
    private int ageYears;   // อายุ (ปี)
    private int ageMonths;  // อายุ (เดือน)
    private boolean isBSOD; // สถานะ BSOD
    private boolean lemonFed; // วัวกินมะนาวหรือไม่

    // Constructor
    public CowModel(String id, String breed, int ageYears, int ageMonths) {
        this.id = id;
        this.breed = breed;
        this.ageYears = ageYears;
        this.ageMonths = ageMonths;
        this.isBSOD = false; // เริ่มต้นไม่มีปัญหา BSOD
        this.lemonFed = false; // เริ่มต้นยังไม่ได้กินมะนาว
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public String getBreed() {
        return breed;
    }

    public int getAgeYears() {
        return ageYears;
    }

    public int getAgeMonths() {
        return ageMonths;
    }

    public boolean isBSOD() {
        return isBSOD;
    }

    public void setBSOD(boolean isBSOD) {
        this.isBSOD = isBSOD;
    }

    public boolean isLemonFed() {
        return lemonFed;
    }

    public void feedLemon() {
        this.lemonFed = true;
    }

    // ฟังก์ชันสุ่มอายุของวัว
    public static int[] randomAge() {
        Random random = new Random();
        int years = random.nextInt(11); // อายุ 0-10 ปี
        int months = random.nextInt(12); // อายุ 0-11 เดือน
        return new int[]{years, months};
    }
}
