package guava_test;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

import java.util.HashSet;

/**
 * 测试multimap类
 *
 * @author alice
 * @version 2016/12/16 15:25
 */
public class TestMultimap {

    public static void main(String[] args) {
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("1", "a1");
        multimap.put("1", "a2");
        multimap.put("2", "b1");
        multimap.put("3", "c1");
        multimap.put("3", "c2");
        multimap.put("3", "c3");
        System.out.println(multimap);//{1=[a1, a2], 2=[b1], 3=[c1, c2, c3]}

        //set的交集, 并集, 差集
        HashSet setA = Sets.newHashSet(1, 2, 3, 4, 5);
        HashSet setB = Sets.newHashSet(4, 5, 6, 7, 8);
        System.out.println(Sets.union(setA, setB));//并集[1, 2, 3, 4, 5, 8, 6, 7]
        System.out.println(Sets.difference(setA, setB));//差集[1, 2, 3]
        System.out.println(Sets.difference(setB, setA));//差集[8, 6, 7]
        System.out.println(Sets.intersection(setA, setB));//交集[4, 5]


       // MapDifference differenceMap = Maps.difference(mapA, mapB);
       // differenceMap.areEqual();
       // Map entriesDiffering = differenceMap.entriesDiffering();
       // Map entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
       // Map entriesOnlyOnRight = differenceMap.entriesOnlyOnRight();
       // Map entriesInCommon = differenceMap.entriesInCommon();

        //验证与条件检查
        int count = 1;
        if (count <= 0) {
            throw new IllegalArgumentException("must be positive: " + count);
        }
        Preconditions.checkArgument(count > 0, "must be positive: %s", count);

        //检测是否为空
        Preconditions.checkNotNull(count);

//        Collections.unmodifiableList();
//        ImmutableList.of(steps);
    }
}
