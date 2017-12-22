package com.like.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

final class DemoUtils {
  int currentOffset;

  DemoUtils() {
  }

  String[] url =new String[]{"http://mvimg1.meitudata.com/5654458af11156661.jpg",
          "http://img4.duitang.com/uploads/item/201512/19/20151219175017_BWiZj.jpeg",
          "http://img3.duitang.com/uploads/item/201604/25/20160425183933_yXERQ.thumb.700_0.jpeg",
          "http://img0.imgtn.bdimg.com/it/u=3157062390,2800572248&fm=214&gp=0.jpg",
          "http://img4.duitang.com/uploads/item/201511/18/20151118125205_Ex5L2.jpeg",
          "http://img4.duitang.com/uploads/item/201511/18/20151118125526_zXe2k.jpeg"};

  public List<DemoItem> moarItems(int qty) {
    List<DemoItem> items = new ArrayList<>();

    for (int i = 0; i < qty; i++) {
      int colSpan = Math.random() < 0.2f ? 2 : 1;
      // Swap the next 2 lines to have items with variable
      // column/row span.
      // int rowSpan = Math.random() < 0.2f ? 2 : 1;
      int rowSpan = colSpan;
      if (i == 0){
        rowSpan =2 ;
        colSpan =2;
      }else {
        rowSpan =1 ;
        colSpan =1;
      }
      DemoItem item = new DemoItem(colSpan, rowSpan, currentOffset + i);
      item.setUrl(url[i]);
      items.add(item);
    }

    currentOffset += qty;

    return items;
  }
}
