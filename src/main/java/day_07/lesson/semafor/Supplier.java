package day_07.lesson.semafor;

public class Supplier {


    private GunWarehouse gunWarehouse = new GunWarehouse();
    private KnifeWarehouse knifeWarehouse = new KnifeWarehouse();
    private BFGWarehouse bfgWarehouse = new BFGWarehouse();

    public void supply(int guns, int knifes, int bfgs) {

        while (0 < guns--) {
            synchronized (gunWarehouse) {
                gunWarehouse.add();
            }
        }

        while (0 < knifes--) {
            synchronized (knifeWarehouse) {
                knifeWarehouse.add();
            }
        }

        while (0 < bfgs--) {
            synchronized (bfgWarehouse) {
                bfgWarehouse.add();
            }
        }
    }
}
