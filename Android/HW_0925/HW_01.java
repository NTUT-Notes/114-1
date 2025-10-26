interface CPR {
    public void doCPR();
}

interface Drive {
    public void doDrive();
}

class Doctor implements Drive, CPR {
    public void show() {
        System.out.println("I am a doctor!");
    }

    public void doCPR() {
        System.out.println("I am a doctor!");
        System.out.println("Of course, I can do CPR!");
    }

    @Override
    public void doDrive() {
        System.out.println("I am a doctor!");
        System.out.println("I can also drive too!");
    }
}

class Driver implements Drive {
    public void show() {
        System.out.println("I am a driver!");
    }

    public void drive() {
        System.out.println("I am a driver!");
        System.out.println("Of course, I can drive!");
    }

    @Override
    public void doDrive() {
        drive();
    }
}

class HW_01 {
    public static void main(String[] args) {
        Doctor doctor = new Doctor();

        doctor.show();
        doctor.doDrive();
        doctor.doCPR();


        Driver driver = new Driver();

        driver.show();
        driver.doDrive();
        
    }
}