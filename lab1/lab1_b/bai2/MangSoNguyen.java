package bai2;
import java.util.Scanner;


public class MangSoNguyen {
    private final int[] a;
    private int n;            // số phần tử hiện có
    private final int max;    // số phần tử tối đa
    private int daSapXep = 0; // 0: chưa sắp xếp, 1: tăng dần, 2: giảm dần

    public MangSoNguyen(int max) {
        this.max = max;
        this.a = new int[max];
        this.n = 0;
    }

    public int getN() { return n; }
    public int getMax() { return max; }

    // Đọc 1 số nguyên từ bàn phím, nhập sai thì nhập lại
    public static int docSo(Scanner sc, String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập số nguyên hợp lệ!");
            }
        }
    }

    // Khởi tạo và nhập giá trị cho mảng
    public void nhap(Scanner sc) {
        int k;
        do {
            k = docSo(sc, "Nhập số phần tử (1.." + max + "): ");
        } while (k < 1 || k > max);
        for (int i = 0; i < k; i++) {
            a[i] = docSo(sc, "a[" + (i + 1) + "] = ");
        }
        n = k;
        daSapXep = 0;
    }

    public void xuat() {
        if (n == 0) {
            System.out.println("Mảng rỗng");
            return;
        }
        System.out.print("Mảng (" + n + "/" + max + " phần tử): ");
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    // Thêm Y vào đầu mảng
    public boolean themDau(int y) {
        return themTai(1, y);
    }

    // Thêm Y vào cuối mảng
    public boolean themCuoi(int y) {
        return themTai(n + 1, y);
    }

    // Thêm Y vào vị trí thứ i (đếm từ 1). Hợp lệ khi 1 <= i <= n + 1
    public boolean themTai(int i, int y) {
        if (n >= max || i < 1 || i > n + 1) {
            return false;
        }
        for (int k = n; k >= i; k--) {
            a[k] = a[k - 1];
        }
        a[i - 1] = y;
        n++;
        daSapXep = 0;
        return true;
    }

    // Xóa tất cả phần tử có giá trị X, trả về số phần tử đã xóa
    public int xoaGiaTri(int x) {
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] != x) {
                a[k++] = a[i];
            }
        }
        int daXoa = n - k;
        n = k;
        if (daXoa > 0) {
            daSapXep = 0;
        }
        return daXoa;
    }

    // Xóa phần tử thứ j (đếm từ 1). Hợp lệ khi 1 <= j <= n
    public boolean xoaViTri(int j) {
        if (j < 1 || j > n) {
            return false;
        }
        for (int k = j - 1; k < n - 1; k++) {
            a[k] = a[k + 1];
        }
        n--;
        daSapXep = 0;
        return true;
    }

    // Sắp xếp Radix Sort (LSD). Số âm được tách ra xử lý riêng
    public void radixSort(boolean tang) {
        int[] am = new int[n];
        int[] duong = new int[n];
        int na = 0, nd = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] < 0) {
                am[na++] = -a[i];
            } else {
                duong[nd++] = a[i];
            }
        }
        radixKhongAm(am, na);
        radixKhongAm(duong, nd);

        // Thứ tự tăng dần: số âm (trị tuyệt đối giảm dần) rồi đến số không âm
        int k = 0;
        for (int i = na - 1; i >= 0; i--) {
            a[k++] = -am[i];
        }
        for (int i = 0; i < nd; i++) {
            a[k++] = duong[i];
        }
        if (!tang) {
            for (int i = 0, j = n - 1; i < j; i++, j--) {
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        daSapXep = tang ? 1 : 2;
    }

    private static void radixKhongAm(int[] arr, int len) {
        if (len <= 1) {
            return;
        }
        int maxV = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] > maxV) {
                maxV = arr[i];
            }
        }
        int[] out = new int[len];
        for (long exp = 1; maxV / exp > 0; exp *= 10) {
            int[] dem = new int[10];
            for (int i = 0; i < len; i++) {
                dem[(int) ((arr[i] / exp) % 10)]++;
            }
            for (int d = 1; d < 10; d++) {
                dem[d] += dem[d - 1];
            }
            for (int i = len - 1; i >= 0; i--) {
                int d = (int) ((arr[i] / exp) % 10);
                out[--dem[d]] = arr[i];
            }
            System.arraycopy(out, 0, arr, 0, len);
        }
    }

    // Tìm tuần tự trong mảng chưa sắp xếp. Trả về vị trí (đếm từ 1) hoặc -1
    public int timTuyenTinh(int b) {
        for (int i = 0; i < n; i++) {
            if (a[i] == b) {
                return i + 1;
            }
        }
        return -1;
    }

    // Tìm nhị phân trong mảng đã sắp xếp (tăng hoặc giảm). Trả về vị trí (đếm từ 1) hoặc -1
    public int timNhiPhan(int b) {
        if (daSapXep == 0) {
            throw new IllegalStateException("Mảng chưa được sắp xếp, hãy sắp xếp trước khi tìm nhị phân");
        }
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = (lo + hi) >>> 1;
            if (a[mid] == b) {
                return mid + 1;
            }
            boolean canTimBenPhai = (daSapXep == 1) ? a[mid] < b : a[mid] > b;
            if (canTimBenPhai) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
