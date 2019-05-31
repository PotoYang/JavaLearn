import com.google.gson.Gson;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by 71579 on 2018/7/4.
 */
public class TTT {

    public static void main(String[] args) {
        D a = new D();
        System.out.println(Double.valueOf(a.getD()) == null ? 0 : 1);
    }

    @Test
    public void mapTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0);
        (cost.stream().map(x -> x + x * 0.05)).forEach(x -> System.out.println(x));
    }

    static class D {

        public D() {
        }

        public double getD() {
            return Double.parseDouble(null);
        }
    }

    @Test
    public void dateTest() throws ParseException {
        String strDate = "2018-05-28 19:28:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.parse(strDate));
    }

    @Test
    public void testCollection() {
        List<String> stringList = new ArrayList<String>() {
            {
                add("111");
                add("222");
                add("333");
                add("444");
            }
        };

        stringList.remove(0);
        stringList.remove(0);
        stringList.add("111");
        System.out.println(stringList.get(2));
    }


    List<AA> aaList = new ArrayList<AA>() {{
        add(new AA());
    }};

    public void aa() {

        List<Integer> datas = new ArrayList<>();
        for (AA aa : aaList) {
            if (aa.getParentId().equals(6)) {
                datas.add(aa.getId());
            }
        }

        getData(datas);
    }

    public void getData(List<Integer> datas) {
        if (datas.size() == 0) {
            return;
        }

        for (Integer data : datas) {
            System.out.println(data);
            List<Integer> datas2 = new ArrayList<>();
            for (AA aa : aaList) {
                if (aa.getParentId().equals(data)) {
                    datas2.add(aa.getId());
                }
            }
            getData(datas2);
        }
    }

    class AA {
        private Integer id;
        private Integer parentId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public void setParentId(Integer parentId) {
            this.parentId = parentId;
        }
    }


    @Test
    public void testReflect() {
        //获取类的字节码文件对象
        try {
            Class c = Class.forName("Student");
            Field[] f = c.getDeclaredFields();
            for (Field fie : f) {
                System.out.println(fie.getName());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sub() {
        String imgUrl = "http://127.0.0.1:8080/cms/ReadAddress/1479805098158.jpg";

        String image = imgUrl.substring(0, imgUrl.lastIndexOf("/"));

        System.out.println(image);
    }

    @Test
    public void insertZero() {
        String newString = String.format("%02d", 15);
        System.out.println("newString === " + newString);
    }

    A jsonObject;

    @Test
    public void generateTree() {
        String str = "{\"label\":\"A\",\"child\":[{\"label\":\"AA\",\"child\":[{\"label\":\"AAA\"},{\"label\":\"AAB\"}]},{\"label\":\"AB\"}]}";

        A a = new A();

        List<A> aaList = new ArrayList<>();
        A aaa = new A();
        aaa.setLabel("AAA");
        aaa.setChild(null);
        aaList.add(aaa);
        A aab = new A();
        aab.setLabel("AAB");
        aab.setChild(null);
        aaList.add(aab);

        List<A> aList = new ArrayList<>();
        A aa = new A();
        A ab = new A();
        aa.setLabel("AA");
        aa.setChild(aaList);
        aList.add(aa);
        ab.setLabel("AB");
        ab.setChild(null);
        aList.add(ab);

        a.setLabel("A");
        a.setChild(aList);

        Gson gson = new Gson();
        System.out.println(gson.toJson(a));

        jsonObject = gson.fromJson(str, A.class);

        getDigui(jsonObject);

        System.out.println("把JSON字符串转为对象///  " + jsonObject.toString());
    }

    private A getDigui(A z) {
        /*if (z.getChild().isEmpty()) {
            return;
        }
        List<A> aChildList = new ArrayList<>();
        for (A aChild:aChildList){
            if (!aChild.getChild().isEmpty()){
                getDigui(aChild);
            }
        }
        return ;*/
        return null;
    }

    class A {
        private String label;
        private List<A> child;

        public String getLabel() {
            return label;
        }

        void setLabel(String label) {
            this.label = label;
        }

        public List<A> getChild() {
            return child;
        }

        public void setChild(List<A> child) {
            this.child = child;
        }

        @Override
        public String toString() {
            return "{" +
                    "label='" + label + '\'' +
                    ", child=" + child +
                    '}';
        }
    }


    @Test
    public void testJson() {
        Gson gson = new Gson();
//        JsonTestA jsonTestA = new JsonTestA();
        List<String> pathList = Arrays.asList(
                "100x123", "360x230", "480x360", "480x360",
                "100x123", "360x230", "480x360", "480x360",
                "100x123", "360x230", "480x360", "480x360",
                "100x123", "360x230", "480x360", "480x360");
        String data = gson.toJson(pathList);
        System.out.println(data);
    }

    @Test
    public void testLocalDateParseNull() {
        String endDate = null;
        LocalDate end = LocalDate.parse(endDate);
    }

    @Test
    public void testClassLoader() {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(TTT.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader());
    }

    @Test
    public void testEnum() {
        Definition[] definitions = Definition.values();
        for (Definition definition : definitions) {
            System.out.println(definition.getIndex() + " " + definition.getName());
        }

        System.out.println(Definition.HIGH_DEFINITION.getIndex());
    }

    @Test
    public void testArrayList() {
        List<Integer> sumList = new ArrayList<>(5);
        for (int j = 0; j < 5; j++) {
            sumList.add(0);
        }
        sumList.forEach(System.out::println);
    }

    @Test
    public void testLocalDateTimeToCron() {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = LocalDateTime.now().atZone(zone).toInstant();
        String cronDateFormat = "ss mm HH dd MM ? yyyy";
        String cron = new SimpleDateFormat(cronDateFormat).format(Date.from(instant));
        System.out.println(cron);
    }

    @Test
    public void testRight() {
        long a = 123456789;
        Float f = Float.parseFloat(String.format("%.2f", a / 1024.0 / 1024.0));
        System.out.println(f);
    }

    @Test
    public void testCrypt() {
        String a = "1-1-2-3-4";
        String secret = "qwertyuiopasdfghjklzxcvbnm";
        byte[] aa = a.getBytes();
        byte[] bb = new byte[aa.length];
        for (int i = 0; i < aa.length; i++) {
            bb[i] = (byte) (aa[i] ^ secret.getBytes()[i]);
        }
        System.out.println();
        for (int i = 0; i < bb.length; i++) {
            System.out.print(bb[i] + " ");
            System.out.print((char) (byte) (bb[i] ^ secret.getBytes()[i]));
        }
    }

    @Test
    public void testDecode() {
        String filePath = "abc123!@#$你好呀";
        System.out.println(filePath);
        try {
            filePath = new String(filePath.getBytes("GBK"), "ISO-8859-1");
            System.out.println(filePath);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNum() {
        Float f = 12345.65F;
        System.out.println(f / 1024);
    }

    @Test
    public void testList() {
        List<String> origin = new ArrayList<>();
        origin.add("123");
        origin.add("456");
        origin.add("789");
        origin.add("123");
        origin.add("789");

//        Set set = new HashSet<>();
        List<String> des = new ArrayList<>();
        for (String str : origin) {
            if (!des.contains(str)) {
                des.add(str);
            }
        }

        for (String str : des) {
            System.out.println(str);
        }

/*        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }*/
    }

    // 保留字
    private static final String RESERVED = "000";
    // 是否离线
    private static final String ONLINE = "0";
    // 区域码
    private static final String AREA_CODE = "0755";
    // 集群编号
    private static final String CLUSTER_NUM = "001";
    // 内容类型
    private static final String CONTENT_TYPE = "002";

    public static String GenerateCdnAssetId(Integer id) {
        long shortCode = 3_000_000_000L + id;
        return RESERVED + ONLINE + AREA_CODE +
                CLUSTER_NUM + CONTENT_TYPE +
                shortCode;
    }

    @Test
    public void testGenerateAssetId() {
        String assetid = GenerateCdnAssetId(1200);
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        System.out.println(assetid);
    }

    @Test
    public void testSuffix() {
        String name = "www.1123.learn.java";
        name = name.substring(name.lastIndexOf("."));
        System.out.println(name);
    }

    @Test
    public void testLRUBaseLinkedHashMap() {
        LinkedHashMap<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>(10, 0.8F, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 6;
            }
        };

        for (int i = 0; i < 5; i++) {
            linkedHashMap.put(i, "value: " + i);
        }

        linkedHashMap.put(5, "value: " + 5);

        linkedHashMap.get(0);
        linkedHashMap.get(1);
        linkedHashMap.get(4);

        linkedHashMap.put(6, "value: " + 6);

        linkedHashMap.forEach((k, v) -> {
            System.out.println(v);
        });
    }

    @Test
    public void testPercent() {
        float usedCapacity = 9.99F;
        Integer pendingCapacity = 10;
        float baseCapacity = 10.00F;
        float percent = (usedCapacity * 1024 + pendingCapacity) / (baseCapacity * 1024) * 100;

        System.out.println(percent);

        if (percent >= 99.99) {
            System.out.println((int) Math.ceil(percent - 99.99));
        } else {
            System.out.println(0);
        }
    }

    @Test
    public void testEqual() {
        int a = 10;
        int b = 10;
        System.out.println(a == b);

        Integer aa = 10;
        Integer bb = 10;
        System.out.println(aa == bb);
        System.out.println(aa.equals(bb));

        String sa = "123";
        String sb = "123";
        System.out.println(sa == sb);
        System.out.println(sa.equals(sb));

        String saa = new String("123");
        String sba = new String("123");

        System.out.println(saa == sba);
        System.out.println(saa.equals(sba));

        a = 18;
        System.out.println((int) (a * 1.8));

        sa = "11/|22/|33/|44/";
        String[] outputUrlList = sa.split("\\|");
        StringBuilder outputUrls = new StringBuilder();
        for (String outputUrl : outputUrlList) {
            outputUrls.append(outputUrl).append("123").append("/|");
        }
        System.out.println(outputUrls.substring(0, outputUrls.length() - 1));
    }

    @Test
    public void testYouyi() {
        int videoBitRate = 11618;
        System.out.println(videoBitRate * 1000 / 10000 * 10000);
        System.out.println(videoBitRate * 1800 / 10000 * 10000);
    }

    @Test
    public void testStringMatch() {
        String type = "GI";
        System.out.println(type.matches("(gif|GIF)"));

        int a = 10;
        int b = 8;
        System.out.println(a * 1.0 / b);

        String poster = "123x123|456x456|789x789";
        List<String> posterScaleList = new ArrayList<>(Arrays.asList("345x345", "567x567", "123x123", "789x789"));
        List<String> posterList = Arrays.asList(poster.split("\\|"));

        posterScaleList.addAll(posterList);
        posterScaleList = posterScaleList.stream().distinct().collect(Collectors.toList());
        posterScaleList.forEach(System.out::println);
    }

    @Test
    public void testSecToTime() {
        int seconds = 38000;
        StringBuilder stringBuilder = new StringBuilder();
        // 转换小时
        int hours = seconds / 3600;
        stringBuilder.append(String.format("%02d", hours)).append(":");
        // 剩余秒数
        seconds = seconds % 3600;
        // 转换分钟
        int minutes = seconds / 60;
        stringBuilder.append(String.format("%02d", minutes)).append(":");
        //剩余秒数
        seconds = seconds % 60;
        stringBuilder.append(String.format("%02d", seconds));
        // 00:10:02
        System.out.println(stringBuilder.toString());
    }

    @Test
    public void testMd5() {

        //定义一个字节数组
        byte[] secretBytes = null;
        try {
            File file = new File("F:/test/1.mp4");
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = new byte[]{};
            int len = inputStream.read(bytes, 0, (int) file.length());
            if (len != file.length()) {
                throw new IOException("不完整");
            }
            //            OutputStream outputStream = new FileOutputStream(file);
//            ByteArrayInputStream inputStream = new ByteArrayInputStream()
            // 生成一个MD5加密计算摘要
            MessageDigest md = MessageDigest.getInstance("MD5");
            //对字符串进行加密
            md.update(bytes);
            //获得加密后的数据
            secretBytes = md.digest();
            // 将加密后的数据转换为16进制数字
            StringBuilder md5code = new StringBuilder(new BigInteger(1, secretBytes).toString(16));// 16进制数字
            // 如果生成数字未满32位，需要前面补0
            for (int i = 0; i < 32 - md5code.length(); i++) {
                md5code.insert(0, "0");
            }
            System.out.println(md5code);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMd52() {
        BigInteger bi = null;
        try {
            byte[] buffer = new byte[8192];
            int len = 0;
            MessageDigest md = MessageDigest.getInstance("MD5");
            File f = new File("F:/test/1.mp4");
            FileInputStream fis = new FileInputStream(f);
            while ((len = fis.read(buffer)) != -1) {
                md.update(buffer, 0, len);
            }
            fis.close();
            byte[] b = md.digest();
            bi = new BigInteger(1, b);
            // 前端
            // 6b24b8a0759c5705bf3a999ac47a1449
            // 后端
            // 6b24b8a0759c5705bf3a999ac47a1449
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        assert bi != null;
        System.out.println(bi.toString(16));
    }

    @Test
    public void testRandomAccessFile() {
        try {
            // 创建 conf 文件，记录文件上传分片数量
            File confFile = new File("F:/data/uploads/0c3e3f279a2742146ac1288403e9530b.conf");
            RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
            // 把该分段标记为 true 表示完成
            accessConfFile.setLength(146);
            accessConfFile.seek(0);
            accessConfFile.write(0);
            accessConfFile.seek(10);
            accessConfFile.write(0);
            accessConfFile.seek(20);
            accessConfFile.write(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDirectory() {
        // 本地存盘路径：F:/data/uploads/{md5}/
        String md5 = "123";
        String uploadDirPath = "F:/data/uploads/" + md5 + "/";
        // 以分片名称存储分片数据(如：0,1,2,3,4...)
        File tmpDir = new File(uploadDirPath);
        if (!tmpDir.exists()) {
            if (!tmpDir.mkdirs()) {
                System.out.println(uploadDirPath + " create failed!");
            }
        }
    }


    @Test
    public void testException() {
        ArrayList arrayList = new ArrayList();
        ListIterator listIterator = arrayList.listIterator();
        listIterator.previous();
    }

    @Test
    public void testReadFile() {
        try {
            File file = new File("F:/视频/无法成为野兽的我们[3].mp4");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            long length = (int) file.length();
            int start = (int) (length * 0.4);
            int end = (int) (length * 0.5);

//            FileInputStream inputStream = new FileInputStream(file);
//            byte[] bytes = new byte[5 << 20];
//            int count = inputStream.read(bytes, 0, 5 << 20);
//            messageDigest.update(bytes);

            int BUFFER_SIZE = 5 << 20;
            int bufferCount = 1 + (end - start) / BUFFER_SIZE;
            MappedByteBuffer[] buffers = new MappedByteBuffer[bufferCount];
            long remaining = end - start;
            for (int i = 0; i < bufferCount; i++) {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
                buffers[i] = randomAccessFile.getChannel().map(FileChannel.MapMode.READ_ONLY,
                        start + i * BUFFER_SIZE, Math.min(remaining, BUFFER_SIZE));
                messageDigest.update(buffers[i]);
                remaining -= BUFFER_SIZE;
            }

//            System.out.println(count);
            System.out.println(new String(Hex.encodeHex(messageDigest.digest())));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFile() {
        File file = new File("F:/test/1.txt");
        A(file);
        B(file);
    }

    private void A(File file) {
        try {
            byte[] data = new byte[]{'a', 'b', 'c', 'd'};
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void B(File file) {
        System.out.println(file.length());
    }

    @Test
    public void testDoubleListAdd() {
        List<Double> list = Arrays.asList(3.0, 4.0, 12.55, 13.22, 11.11, 3.0, 4.0, 12.55, 13.22, 11.11, 3.0, 4.0, 12.55, 13.22, 11.11, 3.0, 4.0, 12.55, 13.22, 11.11);

        List<BigDecimal> bigDecimalList = Arrays.asList(
                new BigDecimal(3.0), new BigDecimal(4.0), new BigDecimal(12.55), new BigDecimal(13.22), new BigDecimal(11.11),
                new BigDecimal(3.0), new BigDecimal(4.0), new BigDecimal(12.55), new BigDecimal(13.22), new BigDecimal(11.11),
                new BigDecimal(3.0), new BigDecimal(4.0), new BigDecimal(12.55), new BigDecimal(13.22), new BigDecimal(11.11),
                new BigDecimal(3.0), new BigDecimal(4.0), new BigDecimal(12.55), new BigDecimal(13.22), new BigDecimal(11.11));
        List<Double> list1 = list.subList(0, 5);
        System.out.println(list1.stream().reduce(Double::sum));
        List<Double> list2 = list.subList(5, 10);
        System.out.println(list1.stream().reduce(Double::sum));
        List<Double> list3 = list.subList(10, 15);
        System.out.println(list1.stream().reduce(Double::sum));
        List<Double> list4 = list.subList(15, 20);
        System.out.println(list1.stream().reduce(Double::sum).get()
                + list2.stream().reduce(Double::sum).get()
                + list3.stream().reduce(Double::sum).get()
                + list4.stream().reduce(Double::sum).get());
        List<BigDecimal> bigDecimalList1 = bigDecimalList.subList(0, 5);
        System.out.println(bigDecimalList1.stream().reduce(BigDecimal::add));
        System.out.println(bigDecimalList.stream().reduce(BigDecimal::add));
    }

    @Test
    public void testTimeFormat() {
        String assetid = "000007550010021100000078";
        int length = assetid.length();
        Integer videoId = Integer.parseInt(assetid.substring(length - 9, length));
        System.out.println(videoId);
    }

    @Test
    public void testVideoInfoGet() {
        VideoInfoUtils.getVideoInfoByXuggler("F:/test/100029263.ts");
    }

    @Test
    public void testStringReplace() {
        StringBuilder s = new StringBuilder("11111000");
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                System.out.println(i + " is 0.");
                break;
            }
        }
    }
}