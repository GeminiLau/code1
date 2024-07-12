import javax.sound.midi.Soundbank;
import java.util.*;

public class Solution {

    /**
     * 检查数组中元素的出现次数是否都是唯一的。
     * 该方法通过HashMap来统计数组中每个元素的出现次数，然后检查这些次数是否有重复的。
     * 如果有元素的出现次数重复，则返回true；如果所有元素的出现次数都是唯一的，则返回false。
     *
     * @param arr 输入的整数数组。
     * @return 如果数组中存在出现次数重复的元素，则返回true；否则返回false。
     */
    public boolean uniqueOccurrences(int[] arr) {
        // 使用HashMap来统计数组中每个元素的出现次数。
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // 如果元素已存在于HashMap中，则将其出现次数加1；否则将其添加到HashMap中，出现次数初始化为1。
            if (hashMap.containsKey(arr[i])){
                hashMap.put(arr[i],hashMap.get(arr[i]).intValue()+1);
            }else {
                hashMap.put(arr[i],1);
            }
        }
        // 遍历HashMap，检查是否有出现次数重复的元素。
        for (HashMap.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            // 如果某个元素的出现次数大于1，则说明有出现次数重复的元素，返回true。
            if(entry.getValue().intValue()>1){
                return true;
            }
        }
        // 如果所有元素的出现次数都是唯一的，则返回false。
        return false;
    }


    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);  // 分区操作
            quickSort(arr, left, pivotIndex - 1);   // 对左子数组进行递归排序
            quickSort(arr, pivotIndex + 1, right);  // 对右子数组进行递归排序
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//        int[] temp = new int[arr.length];
//        int j = 0;
//
//        for (int i = 0; i < arr.length - 1; i++) {
//            if (arr[i] != arr[i + 1]) {
//                temp[j++] = arr[i];
//            }
//        }
//
//        temp[j++] = arr[arr.length - 1];
//
//        int[] result = new int[j];
//        for (int i = 0; i < j; i++) {
//            result[i] = temp[i];
//        }
//        return result;

        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];  // 选择最右边的元素作为基准
        int i = left - 1;  // i是小于基准元素的最后一个元素的索引

        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);  // 交换arr[i]和arr[j]
            }
        }

        swap(arr, i + 1, right);  // 将基准元素放置到正确的位置
        return i + 1;  // 返回基准元素的位置
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static int Count_res(int[] arr){
        int[] data=Solution.quickSort(arr,0,arr.length-1);
        int l=1,r=1;
        while (r<data.length){
            if (data[r]!=data[l-1]){
                data[l++]=data[r];
            }
            r++;
        }
        int[] result=new int[l];
        for (int i = 0; i < l; i++) {
            result[i]=data[i];
        }

        int res=0;
        for (int i = 0; i < result.length-2 ; i++) {
            if (result[i]+1==result[i+1]&&result[i]+2==result[i+2])
                res++;

        }
        return res;
    }

    public static boolean isyuan(char c){
        if (c=='a'||c=='e'||c=='i'||c=='o'||c=='u'){
            return true;
        }
        return false;
    }
    public static String insert(String s,int k,char c){
        //在第k个位置进行 插入c
        return s.substring(0,k)+c+s.substring(k);
    }
    public static String Separation(String str){
        int l=0,bracket=0,count=0;
        char[] brackets={'(',')'};
        String result=str;
        for (int i = 0; i < str.length(); i++,l++) {
            if (isyuan(str.charAt(i))){
                result=insert(result,l+count,brackets[bracket]);
                count++;
                bracket=bracket^1;
                if (bracket==0){
                    result=insert(result,l+count,brackets[bracket]);
                    bracket=bracket^1;
                    count++;
                }

            }

        }
        result=result+")";
        return result;

    }


    public static int[] runningSum(int[] nums) {
        int sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum+=nums[i];
            nums[i]=sum;
        }
        return nums;
    }

    public static boolean judge(int[] nums, int n, int begin, int m,int k){
        //n为长度,begin 为开始部分
        int count=0;
        for (int i = 0; i < n; i++) {
            if (nums[begin+i]>=m){
                count++;
            }
        }
        if (count>=k){
            return true;
        }else {
            return false;
        }
    }
    public static int pickContinueStr(int[] datas,int m,int k){
        //从str中挑选连续的子串，要求至少有k个值大于等于m
        //从长度k开始挑选，然后判断每个长度的话是否满足条件
        int sum=0;
        for (int n = k; n <= datas.length; n++) {
            for (int i = 0; i < datas.length-n+1; i++) {
                if (judge(datas,n,i,m,k)){
                    sum++;
                }
            }
        }
        return sum;

    }

    /**
     * 计算数组中可移动的递增子数组的数量。
     * 该方法通过遍历所有可能的子数组来检查它们是否是递增的。如果是递增的，则计数器增加。
     * 这里定义的“可移动”意味着子数组可以在原数组中移动位置，而不改变其递增的性质。
     *
     * @param nums 输入的整数数组。
     * @return 返回数组中可移动的递增子数组的数量。
     */
    public static int increasingMovableSubarrayCount(int[] nums) {
        /* 初始化计数器为0，用于记录递增子数组的数量 */
        int count = 0;
        if (nums.length == 0){
            return 0;
        }
        /* 外层循环遍历数组的每一个起始位置 */
        for (int i = 0; i < nums.length; i++) {
            /* 内层循环遍历从当前起始位置到数组的末尾 */
            for (int j = i; j < nums.length; j++) {
                /* 调用isIncreasingArray方法检查当前子数组是否递增 */
                boolean result = isIncreasingArray(nums, i, j);
                /* 如果子数组递增，则计数器增加 */
                if (result) {
                    count++;
                }
            }
        }
        /* 返回计数器的值，即递增子数组的数量 */
        return count;
    }


    /**
     * 检查数组抛开子序列之后是否是递增的。
     * 该方法通过创建一个临时数组来存储剩余序列，然后检查这个序列是否递增。
     *
     * @param nums 原始数组
     * @param i 抛开子序列的起始索引
     * @param j 抛开子序列的结束索引
     * @return 如果子序列递增，则返回true；否则返回false。
     */
    public static boolean isIncreasingArray(int[] nums,int i,int j){
        // 创建一个长度为50的临时数组，用于存储子序列
        int[] temp=new int[50];
        //说明删除子数组后剩余数组为空
        if (nums.length==(j-i+1)){
            return true;
        }
        //说明删除子数组后剩余数组为1
        if (nums.length==(j-i+1)+1)
            return true;
        // 遍历原始数组，根据子序列的起始和结束索引，将对应元素复制到临时数组
        for (int k = 0; k < nums.length-j+i-1; k++) {
            // 如果当前索引小于起始索引i，则将当前元素复制到临时数组
            if (k<i){
                temp[k]=nums[k];
            }
            // 如果当前索引大于等于起始索引i，则将对应子序列的元素复制到临时数组
            if (k>=i){
                temp[k]=nums[k+j-i+1];
            }
        }

        // 遍历临时数组，检查是否存在非递增的元素
        for (int k = 0; k < (nums.length-j+i-1)-1; k++) {
            // 如果当前元素大于下一个元素，则子序列不是递增的，返回false
            if (temp[k]>=temp[k+1]){
                return false;
            }
        }

        // 如果临时数组中的所有元素都是递增的，则返回true
        return true;
    }

    public int minimumDistance(int[][] points) {
                final int INF = Integer.MAX_VALUE;
                int maxX1 = -INF, maxX2 = -INF, maxY1 = -INF, maxY2 = -INF;
                int minX1 = INF, minX2 = INF, minY1 = INF, minY2 = INF;
                int maxXi = 0, minXi = 0, maxYi = 0, minYi = 0;

                for (int i = 0; i < points.length; i++) {
                    int[] p = points[i];
                    int x = p[0] + p[1];
                    int y = p[1] - p[0];

                    // x 最大次大
                    if (x > maxX1) {
                        maxX2 = maxX1;
                        maxX1 = x;
                        maxXi = i;
                    } else if (x > maxX2) {
                        maxX2 = x;
                    }

                    // x 最小次小
                    if (x < minX1) {
                        minX2 = minX1;
                        minX1 = x;
                        minXi = i;
                    } else if (x < minX2) {
                        minX2 = x;
                    }

                    // y 最大次大
                    if (y > maxY1) {
                        maxY2 = maxY1;
                        maxY1 = y;
                        maxYi = i;
                    } else if (y > maxY2) {
                        maxY2 = y;
                    }

                    // y 最小次小
                    if (y < minY1) {
                        minY2 = minY1;
                        minY1 = y;
                        minYi = i;
                    } else if (y < minY2) {
                        minY2 = y;
                    }
                }

                int ans = INF;
                for (int i : new int[]{maxXi, minXi, maxYi, minYi}) {
                    int dx = (i == maxXi ? maxX2 : maxX1) - (i == minXi ? minX2 : minX1);
                    int dy = (i == maxYi ? maxY2 : maxY1) - (i == minYi ? minY2 : minY1);
                    ans = Math.min(ans, Math.max(dx, dy));
                }
                return ans;

    }

    //给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
    // s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
    //例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
    // "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
    //返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
    public List<Integer> findSubstring(String s, String[] words) {
        //获得words长度和s的长度
        int n = words.length;
        int m = s.length();
        //获得words里面每个单词的长度
        int len = words[0].length();
        //将words数组转成ArrayList
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList(words));
        ArrayList<String> wordList_copy = new ArrayList<>(Arrays.asList(words));
        //创建一个新的空List
        List<Integer> res = new ArrayList<>();
        String str1=s,str2=str1;
        String cut="";
        int index1=-1,index2=0;
        for (int i = 0; i < n; i++) {
            //找到words数组中每轮单词在s中的位置，作为子字符串的开头
            while (str2.indexOf(words[i])!=-1){
                index1=str2.indexOf(words[i]);
                index2=index1;
                str2=str2.substring(index1+len);
                //在wordList里面去除这个单词
                wordList.remove(words[i]);
                while (wordList.size()!=0 && wordList.contains(str2.substring(0,len))){
                    wordList.remove(str2.substring(0,len));
                    str2=str2.substring(len);
                }
                if (wordList.size()==0){

                    //将index1插入res
                    res.add(m-str2.length()-n*len);
                }
                wordList=new ArrayList<>(wordList_copy);
            }
            str2=str1;
        }
        return res;

    }


    public static void main(String[] args) {
        //调用findSubstring方法，s = "barfoothefoobarman", words = ["foo","bar"]
        Solution solution = new Solution();
        List<Integer> res = solution.findSubstring("barfoofoobarthefoobarman", new String[]{"bar","foo","the"});
        System.out.println(res);

    }



}
