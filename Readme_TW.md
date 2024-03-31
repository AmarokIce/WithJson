# With Json ( OVO )

**| [简体中文](Readme_CN.md) | >臺灣正體< | [English](Readme.md) |** <br />
**|如何修改魔法金屬（施工中）|**

> ## Menu
> ###  <a href="#關於">  **關於**                </a>
>   1. <a href="#ovo">    這是什麼？               </a>
>   2. <a href="#start">  如何開始？               </a>
>   3. <a href="#plan">   未來計劃                    </a>
> 
> ### <a href="#goStart"> **讓我們開始！!**            </a>
> ### <a href="#基本">    基本                       </a>
>   * <a href="#package"> OVO資料夾與OVOPackage      </a>
>   * <a href="#modid">   創建Modid                  </a>
>
> ### <a href="#創作">    創作                </a>
>   * <a href="#tab">     創造模式欄          </a>
>   * <a href="#item">    物品                </a>
>   * <a href="#block">   方塊                </a>
>   * <a href="#food">    食物                </a>
>   * <a href="#gift">    禮物                </a>
>   * <a href="#tool">    工具                </a>
>   * <a href="#weapon">  武器                </a>
>   * <a href="#食譜">    食譜                </a>
>   * <a href="#biome">   生物系              </a>
>   * <a href="#texture"> 資源包              </a>
>   * <a href="#error">   嘿！它没有用！       </a>
>
> ### **其它**
> * 許可證
> * 前置庫

## **關於**
### **這是什麼？**<a id="ovo"></a>
WithJson 是一個Minecraft模組，勵志於讓你能夠使用Json “編碼” ！<br />
現在WithJson 能夠讓你完全使用Json創造一些遊戲元素而你無需學習如何使用Java編碼！<br />

### **如何開始？**<a id="start"></a>
我們需要在你的Minecraft遊戲根目錄創建一個`ovo`資料夾，然後你要做的請閱讀<a href="#package">*OVO資料夾與OVOPackage*</a>。 <br />
**<font color=Red>注意：為了向你展示它是如何運行的，我將會使用“//”作為註解塊。但是它們不應該出現在你的文檔中，因為Json沒有註解塊標記！**</font>

### **未來計劃**<a id="plan"></a>
我們有一些將要實現的計劃，比如讓WithJson 能夠解析Jar中的文檔以及昂你能夠創建一套護甲，以及更多🥓！<br />
如果你有一些想法，提交一份Issues吧！但是如果這個想法的實現在代碼中是不安全的，那麼我可能會將它移出計劃。很抱歉！但是我不希望看到玩家因為不安全的代碼導致的崩潰而頭疼。

## **讓我們開始！！**<a id="goStart"></a>
### **基本**
這裡有一些簡單的概念是你需要知道的。 <p />

 **OVO資料夾與OVOPackage** <a id="package"></a> <br />
WithJson 不會創建任何文檔，它只會解析文檔。<br />
所以我們需要自己創建`ovo`資料夾，然後把東西放進去。<br />
“OVOPackage”是資料夾中的資料夾，而每個“OVOPackage”都是一個“偽造的模組”。一個模組就需要一個“Modid”。那麼，現在讓我們在`ovo`資料夾中創建一個`ovo_package.json`文檔。然後在`ovo`資料夾中創建一個任意你喜歡的名字的資料夾，我創建的資料夾名字是`pineapple_test_package`，現在我的`ovo`資料夾裡看起來是這樣：
```
dir: .minecraft

> ovo
>> pineapple_test_package
>> ovo_package.json
```
很好！现在打开`ovo_package.json`，這是我們用於管理OVOPackage的Json文檔，你需要把你所擁有的OVOPackage登記到裡面。現在我的`ovo_package.json`看起來是這樣：
```Json5
[
    "pineapple_test_package"
]
```
如果我們有超多的OVOPackage，那麼看起來是這樣：
```Json5
[
    "pineapple_test_package",
    "other_package"
]
```
*注意：這時候`pineapple_test_package`的解析會優先於`other_package`。*

### **創建Modid** <a id="modid"></a><br />
很好！那麼你知道的，“一個模組需要一個Modid”對吧？進入你剛創建的資料夾並創建`info.json`，這是你的Modid管理文檔。<br />
現在你可以隨意的創建一個你喜歡的名字，但你只能使用ASCALL字符集的字符。
```Json5
{
    "modid": "pineapple"
}
```
好極了！你現在做好了全部準備的工作！幹的好！ <br />
如果你的Modid非常奇怪以至於無法被解析，那麼WithJson會接管你的Modid並改為“ovo”。
<br /><br />

## **創作**
### **創造模式欄** <a id="tab"></a>
好哦！我們終於可以開始！<br />
為我們的物品創建一個`創造模式欄`吧！<br />
為什麼不可以使用香草的創造模式物品組？很快就可以了！因為我花費了一些時間思考如何如何使用它們而不會有任何意外。~~那麼我們會有那麼多意外嗎？~~<br />

創建文檔`Group.json`。創建一個新的物品組！
```Json5
[
  {
    "name": "pineappleGroup",   // 你的物品組的名稱，語言鍵為"itemGroup.%YourGroupName"
    "icon": "minecraft:apple"   // 本地註冊名或物品ID，如果找不到物品會變成蘋果
  },
  {                             // 如果我們需要更多組別...
    ......
  }
]
```

### **Item**<a id="item"></a>
完成！現在創建一個物品！<br />
創建一個**文檔或資料夾** ，命名為`Item.json` （如果是資料夾，`Item`）。
如果它是一個資料夾，那麼裡面全部的文檔都會被解析！如果是一個文檔，那麼所有物品必須在這個文檔的列表裡。這裡是示例：

**File:**
```Json5
file: Item.json

[
  {
    "name": "itemtest",               // 物品註冊名
    "localization_name": "itemtest",  // 物品本地名
    "texture_name": "itemtest",       // 物品材質名
    "max_size": 64,                   // 最大堆疊量
    "group": "pineappleGroup"         // 創造模式組
  },
  {                                   // 更多炫酷的... 
    ...
  }
]
```

**Folder:**
```Json5
dir: Item

file: itemtest.json

[
  {
    "name": "itemtest",               // 物品註冊名
    "localization_name": "itemtest",  // 物品本地名
    "texture_name": "itemtest",       // 物品材質名
    "max_size": 64,                   // 最大堆疊
    "group": "pineappleGroup"         // 創造模式組
  },
  {                                   // 更多炫酷的... 
    ...
  }
]

file: itemtest2.json
[
  {
    ...
  }
]
```

> **所以什么是“註冊名”， “本地名”和“材質名”？** <br />
>
> 註冊名是物品的唯一名稱，如果有兩個或更多的物品擁有相同的註冊名，那麼遊戲會因此崩潰。<br />
>
> 本地名是語言文檔中的鍵名，如果兩個或更多的物品擁有相同的本地名，那麼它們在遊戲中的名稱也是相同的。本地鍵名的祝賀規則是：如果它們是物品，組合為“item.%UnlocalName.name", 如果它們是方塊，組合為“tile.%UnlocalName.name”。<br />
>
> 材質名是材質文檔的名稱，如歌兩個或者更多的物品擁有相同的材質名，那麼它們在遊戲裡看起來是一樣的。



### **方塊**<a id="block"></a>
就像物品，一個文檔或資料夾。命名為`Block.json`（如果是資料夾，`Block`。）<br />
讓我們開始：
```Json5
[
  {
    "name": "pineapple_block",
    "localization_name": "pineapple_block",
    "texture_name": "pineapple_block",      // 與物品相同
    "hard": 5,                              // 硬度影響挖掘效率.
    "hard_level": 1,                        // 方塊有多硬？香草的挖掘等級範圍是0-3，對應Wood（木），Stone（石），Iron（鐵），Diamond（鑽）
    "break_tool": "pickaxe",                // 需要哪種工具：pickaxe（鎬）， axe（斧）， 或 shovel（鍬）
    "is_glow": false,                       // 會發光嗎？如果為true，會發出15等的光照
    "drop_item": "pineapple:pineapple",     // 被破壞時會掉落什麼？填入“null”表示不掉落。或填入物品註冊名或物品ID
    "group": "pineappleGroup"
  }
]
```

### **食物**<a id="food"></a>
食物的命名為 `ItemFood.json`( 如果是資料夾, `ItemFood`) <br />
```Json5
[
  {
    "name": "pineapple",
    "localization_name": "pineapple",
    "texture_name": "pineapple",
    "max_size": 64,
    "hunger": 5,                              // 食用後恢復多少飢餓？整數
    "saturation": 0.8,                        // 食用後恢復多少飽和？2.0為满，浮點
    "wolf": true,                             // 狼和狗會吃它嗎？
    "always_eat": false,                      // 能夠吃不停嗎？（就像金蘋果）
    "fast_food": false,                       // 吃的很快？
    "isDrink": false,                         // 是飲品？
    "return_item": "null",                    // 吃完返回什麼？填入“null”表示不返還，或是物品註冊名或物品ID
    "group": "pineappleGroup"
  }
]
```

### **禮物**<a id="gift"></a>
聖誕快樂! 禮物是玩家右擊後會給玩家一些物品的物品。命名為 `ItemGift.json` ( 如果是資料夾, `ItemGift`) <br />
這是一個測試內容，未來可能會重構（可能？）
```Json5
[
    {
        "name": "pineapple_package",
        "localization_name": "pineapple_package",
        "texture_name": "pineapple",
        "max_size": 1,
        "items": [                                  // 列表中的內容都會提供到玩家！
            "pineapple:pineapple",
            "pineapple:pineapple"
        ],
        "group": "pineappleGroup"
    }
]
```

### **工具**<a id="tool"></a>
有什麼比一個工具更好的？兩個工具！命名為 `ItemTool.json` (如果是資料夾, `ItemTool`)
```Json5
[
  {
    "name": "pineapple_pickaxe",
    "localization_name": "pineapple_pickaxe",
    "texture_name": "pineapple_pickaxe",
    "toolkit": "pickaxe",                     // 工具類型: pickaxe, axe, shovel, hoe
    "tool_meta": "iron",                      // 工具材質將會影響工具效率與耐久等工具元素: wood, stone, iron, gold, diamond
    "group": "pineappleGroup"
  }
]
```

### **武器**<a id="weapon"></a>
~~我不知道要说点什么让武器看起来酷一些了。~~ 命名 `ItemWeapons.json` (如果是資料夾, `ItemWeapons`)
```Json5
[
    {
        "name": "pineapple_sword",
        "localization_name": "pineapple_sword",
        "texture_name": "pineapple_sword",
        "tool_meta": "gold",                    // 同工具
        "group": "pineappleGroup"
    }
]
```

### **食譜**
命名為 `Recipe.json` (如果是資料夾, `Recipe`)<br />
如果食譜中有任何錯誤，可能會直接導致遊戲崩潰。 <br />
**舊版** <br />
在WithJson 0.1.0之前，所有的食譜必須使用舊版。
```Json5
[
  {
    "recipe": "smelting",           // 熔爐食譜
    "output": "minecraft:apple",    // 產出物
    "items_list": [                 // 僅第一個有效，註冊名或物品ID
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shapeless", // 無序食譜 
    "output": "minecraft:apple",    // 產出物
    "items_list": [                 // 範圍可選1-9個物品
      "minecraft:apple"
    ]
  },
  {
    "recipe": "crafting_shaped",  // 有序食譜
    "output": "minecraft:apple",  // 產出物
    "items_list": [               // 前三位是順序排序
      "A A",
      " B ",
      "   ",
      "A", "minecraft:apple",     // 0.0.2版本開始允許你自定字符！
      "B", "pineapple:pineapple"  // 我的鳳梨！
    ]
  },
  {
    "recipe": "crafting_shaped",
    "output": "minecraft:apple",
    "items_list": [
      "AA",                       // 嘗試胰腺癌這樣的效果？
      "A", "minecraft:apple"
    ]
  }
]
```

**新版** <br />
0.1.0, 現在Recipes.json看起來更像數據包了。
```Json5
[
  {
    "type": "minecraft:crafting_shaped", // 有序食譜
    "pattern": [
      "AAA",
      "   ",
      "AAA"
    ],
    "key": {
      "A": {
        "item": "minecraft:apple"
      }
    },
    "result": {
      "item": "minecraft:apple",
      "count": "10"                     // 這裡是字符串... 未來會變成整數（#1)
    }
  },
  {
    "type": "minecraft:crafting_shapeless", // 无序食譜
    "ingredients": [
      {
        "oredict": "plankWood"              // 是的！礦物辭典！
      },
      {
        "oredict": "plankWood"
      },
      {
        "oredict": "plankWood"
      },
      {
        "item": "minecraft:apple"
      },
      {
        "item": "minecraft:apple"
      },
      {
        "item": "minecraft:apple"
      }
    ],
    "result": {
      "item": "pineapple:pineapple",
      "count": "5"
    }
  },
  {
    "type": "minecraft:smelting", // 熔爐食譜
    "ingredients": [
      {
        "item": "minecraft:apple" // 僅第一個有效
      }
    ],
    "result": {
      "item": "minecraft:apple",
      "count": "2"
    }
  }
]
```

**删除食譜** <br />
刪除一個遊戲中的食譜，來自香草或模組。命名為`DeleteRecipe.json` (如果是資料夾, `DeleteRecipe` .) 
```Json5
[
  "minecraft:bread",
  "minecraft:cake",
  "minecraft:cooked_potato"
]
```

### **生物系**
一個新的生物系準備好在世界生成！命名為 `Biomes` (如果是資料夾, `Biomes` .)<br />
新增一個新的生物系會複雜於其他的。
```Json5
[
    {
        "id": 123,                      // 生物系的ID，不可與其他生物系的ID相同！
        "name": "Pineapple Biome",      // 生物系的名稱。和其他的生物系相同？都可以！
        "weight": 10,                   // 生成權重
        "weather_type": "WARM",         // 氣候標籤，見下方
        "biomes_tag": "HOT",            // 生物系標籤，見下方
        "flower": true,                 // 會生成花嗎？
        "color": 114514,                // 顏色，十進制的顏色。
        "grass_color": 114514,          // 草的顏色，十進制的顏色。
        "rain": true,                   // 會下雨（雪）嗎？
        "water_color": 114514           // 水的顏色，十進制的顏色。
    }
]
```

> **氣候標籤:** <br />
> 會決定它下雨或是下雪 <br />
> | Type | Rain or Snowy |
> |------|---------------|
> |DESERT|无             |
> |WARM  |雨             |
> |COOL  |雨             |
> |ICY   |雪             |

> **生物系標籤** <br />
> 它會在哪裡生成? <br /> 
> <br /> *基本*       : HOT，COLD
> <br /> *密度*       : SPARSE，DENSE
> <br /> *乾燥*       : WET，DRY
> <br /> *生物系*     : SAVANNA，CONIFEROUS，JUNGLE，SPOOKY，DEAD，LUSH，NETHER，END，MUSHROOM，MAGICAL，OCEAN，RIVER
> <br /> *複合*       : WATER (Ocean and river)
> <br /> *高級*       : MESA，FOREST，PLAINS，MOUNTAIN，HILLS，SWAMP，SANDY，SNOWY，WASTELAND，BEACH


### **資源包** <a id="texture"></a>
製作一個資源包或在某個模組的Jar文檔中新增。<br />
進入assets資料夾，創建一個與你`info.json`中相同的Modid的資料夾。<br />
創建這些資料夾: `lang` 和 `textures`。 材質資料夾中需要區分方塊與物品的資料夾。就像這樣： <br />
```
dir: pineapple

> assets
> > lang
> > > en_US.lang            // 主要的語言文檔！如果無法找到對應的語言文檔將會默認讀取en_US.json。 **因此不要讓他空著！**
> > > zh_CN.lang
> > > zh_TW.lang
> > textures
> > > blocks
> > > > pineapple_block.png // 是的！材質名稱！
> > > items
> > > > pineapple.png       // 是的！材質名稱!
.......
```

### **嘿！它沒有用！** <a id="error"></a>
哦不！太糟糕了！但是請放鬆，因為我會提醒你一些你可能會犯下的錯誤。犯錯是令狼沮喪的，但是這也是一部分！不用擔心，修復它們也是很炫酷的過程！<br />

> **一個錯誤的註冊名** <br />
> 這是一個常見的錯誤，讓我們重新檢查物品（和方塊）的名稱。一個Minecraft 1.7.10本地化註冊名是“Modid + ItemRegisterName”的組合主車門（本地化名和材質名在1.7.10中是允許不一樣的！）。或許我們需要一個工具來幫助我們導出所有物品的本地化註冊名...？ ~~好主意！加入計劃！（？）~~ 
> <br />
>
> **Json結構錯誤** <br />
> 另一個常見的錯誤~~，而且我經常出現~~！一個“，”？或者你缺少了必要的元素？擁有默認參數很快就會完成！（#1）
> <br />
>
> **一个注解** <br />
> 哦不！Json沒有註解塊！再去看看 *如何開始* 的注意事項！
> <br /> 
> 
> **缺少“info.json”， 或者你沒有讓你的ovopackage在“info.json”中登記** <br />
> 請確定ovopackage是被登記的。
> <br />
>
> **我很確定是WithJson的問題！** <br />
> 提交一份Issues會讓WithJson做的更好！感激不盡! :)
>

## **其他**
### **許可證**
**[Mozilla Public License Version 2.0](LICENSE.txt)**

### 贊助
[![](https://raw.githubusercontent.com/AmarokIce/AmarokIce/main/img/AiFaDian.png)](https://afdian.net/a/ut_ice)
[![](https://raw.githubusercontent.com/AmarokIce/AmarokIce/main/img/BuyMeACoffee.png)](https://www.buymeacoffee.com/AmarokIce)
