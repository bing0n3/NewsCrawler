# NewCrawler

一个新闻抓取爬虫.  
爬虫基于Crawler4j这一Java的开源爬虫框架. 


## 设计思路


新闻网址来源于各个新闻网站的rss订阅列表。
首先抓去出新闻中文章的所在标签,再进行一系列的标签过滤,得到一个较好的结果

近期Google推出了APM网页标准,用于提高排版,并给予了支持该标准的网页较 高的搜索排名. 
所以大多数国外新闻站点都支持了这一个特性.我们能够利用该标准,较快的写出标签的提取和过滤规则.



## 目标抓取站点


* [ ] [The Guardia](http://www.theguardian.com)
* [ ] [HuffingtonPost](http://www.huffingtonpost.com/)
* [ ] [CNN](http://www.cnn.com/)
* [ ] [ABC News](http://abcnews.go.com)
* [ ] [Washington Post](http://www.washingtonpost.com)
* [ ] [Fox News](http://www.foxnews.com)
* [ ] [US Today](http://www.usatoday.com)
* [ ] [LA Times](http://www.latimes.com)
* [ ] [New York Times](http://www.nytimes.com)
* [x] [BBC](http://www.bbc.com)
* [ ] [Google News](https://news.google.com)
* [ ] [NBC News](http://www.nbcnews.com)
* [ ] [Mail Oneline](http://www.dailymail.co.uk/ushome/index.htm) 
* [ ] [WSJ](http://www.wsj.com)


## 例子

### 解析出的html

```html
<p>Seventy years ago, in August 1947, British colonial rule in India came to an end. The country was divided into two independent states - Hindu-majority India and Muslim-majority Pakistan. Using letters and diaries sourced from the world's first Partition Museum which opens in Amritsar on 17 August, Soutik Biswas pieces together the extraordinary story of four friends who were separated by the traumatic event and reunited 30 years later. </p> 
<p>"Our country has been broken; the great, sound pulsating heart of India has been broken," a young man in Lahore, Pakistan, wrote to his best friend in Delhi, the capital of India, in the summer of 1949. </p> 
<p>Writing in elegant cursive and turquoise blue ink, Asaf Khwaja had poured his heart out to Amar Kapur. Barely two years had passed since they had been separated by the bloody partition which split the subcontinent into the new independent nations of India and Pakistan. </p> 
<p>"We in Lahore, your friends and former playmates, those who were in school with you and in college and whose first 25 years of life, are inseparably linked with those of yours assure you with the utmost sincerity that distance has not made the slightest difference in our love and affection for you; that we remember you, and remembered you often, with the same brotherly feeling that for so long characterised our relations," wrote Asaf, who had just joined the Pakistan Times newspaper as a journalist. </p> 
<p>"We have spent good times, Amar, grand times, together." </p> 
<p>Amar Kapur, Asaf Khwaja, Agha Raza and Rishad Haider were like a brotherhood of friends. </p> 
<p>They lived within a three-mile radius, visited each other's homes, shared street snacks on the way home from convent school, studied in the same college and played soft ball cricket with twigs for stumps. From innocent boyhood to callow youth, they had shared the good times. </p> 
<p>Then, in the tumultuous summer of 1947, hard times arrived with a vengeance. </p> 
<p>Amar's separation had hurt the most. He was the only Hindu in the group, and his friends called him Punditji, which means a Hindu priest. </p> 
<p>Three weeks after the partition in August 1947, Amar and his family abandoned their sprawling family home and 57-year-old printing business in Lahore, and joined the millions of refugees that crossed the border in what was one of the greatest migrations in human history. </p> 
<p>Two years later, in Delhi, they were still trying to salvage their lives from the detritus of partition. </p> 
<p>Back in their severed homeland, Asaf, Agha and Rishad had entered adulthood and were starting to earn a living. </p> 
<p>Asaf's mordant wit was on magnificent display as he shared the news about their friends. </p> 
<p>"Agha and Rishad have entered into business - the swindlers. They are running an agency for Burmah Shell Company and minting a good bit of money. I wish you could see [Agha] Ahmad. He is (sic) grown so fat and bald that you would find it hard to recognise him - signs of prosperity!" Asaf wrote. </p> 
<p>Asaf was a pragmatic idealist. He loved cricket, poetry and the mountains and developed a love for contract bridge in his later life. He would sometimes spend his summers with his grandfather on a houseboat on Kashmir's Dal Lake or visit the unspoilt mountains of Swat. He was also hopeful about a brighter future for both nations. </p> 
<h2>Partition of India in August 1947 </h2> 
<ul> 
 <li>Perhaps the biggest movement of people in history, outside war and famine. </li> 
 <li>Two newly-independent states were created - India and Pakistan. </li> 
 <li>About 12 million people became refugees. Between half a million and a million people were killed in religious violence. </li> 
 <li>Tens of thousands of women were abducted. </li> 
 <li>This article is part of a BBC series looking at Partition 70 years on. </li> 
</ul> 
<p>Read more: </p> 
<p> Partition 70 years on: The turmoil, trauma - and legacy </p>       
<p>"Much suffering has been caused and much bitterness engendered," he wrote to Amar. "But what is done cannot be undone. All we can do now is to make amends for our past mistakes and work wholeheartedly for the restoration of peace and goodwill among the divided sections of the people." </p> 
<p>But Amar was less buoyant. Riots had broken out in Lahore - a Muslim-majority city where businesses were dominated by non-Muslims - in the months before partition. Under the smoke-filled skies, Hindus and Muslims had turned on each other, burnt down properties, and looted shops and homes. </p> 
<p>His father had forbidden the children and women in the house to step outside. When his family finally left Lahore in September in a convoy of cars, led by his father's grey Opel, he hid a .38 calibre revolver in the door lining. </p> 
<p>"It was madness, complete madness," Amar Kapur, now 94, told me recently. </p> 
<p>He kept a diary after migrating to Delhi following the blood-drenched summer of 1947 via the border city of Amritsar, where the family spent three months on the veranda of a house. In Delhi, the Kapurs then lived without electricity for three years in three rooms in a disputed house. </p> 
<p>"On 3 June 1947 it was decided that India would be partitioned and Pakistan would come into being. On that day was India doomed," Amar wrote in his diary. He wrote that violence hadn't stopped since the announcement. </p> 
<p>"Religion, which should be a strictly private affair and the concern of the individual, was being used to cover up beastly acts of murder and other inhuman acts," he wrote. </p> 
<p>Asaf, in Lahore, believed none of this would affect their friendship. "We have common memories and common experiences that bind us so closely together that no adventitious circumstances can wrench us apart," he wrote in one of his letters. </p> 
<p>But separated by distance, experience and time, the four friends did get separated. For three decades, they completely lost touch. Keeping friendships alive in rival, hostile nations was difficult, not least because it was hard to get visas to visit each other's countries. They also lost each other's addresses. </p> 
<ul> 
 <li> A Country Divided </li> 
 <li> How a jacket and a briefcase shaped a partition love story </li> 
 <li> Cyril Radcliffe: The man who drew the partition line </li> 
</ul> 
<p>A simple twist of fate brought the four together again, however. </p> 
<p>In the summer of 1980, an uncle of Agha Raza visited Delhi to attend a conference. Before he left, Agha had asked him to try trace Amar and his whereabouts. He told him that his family owned a printing press business in Delhi which bore the Kapur family name. </p> 
<p>Agha had been the maverick of the quartet. He had worked for an oil company, joined the Pakistani navy as an officer and then worked for the labour department. In his thirties, he retired to the countryside to look after his family farm, some 120km (74 miles) from Lahore. His friends called him the agriculturist. </p> 
<p>Now, he was on the hunt for his long-lost friend. </p> 
<p>In Delhi his uncle, a former diplomat, looked up the telephone directory and began calling all the Amar Kapurs. He got lucky with the fourth call, and returned to Pakistan with Amar's address and phone number. Soon the friends reconnected, speaking on the phone and writing to each other. </p> 
<p>They shared notes about themselves and their families - all of them were now married with children - and work. There was lot of catching up to do. </p> 
<p>Rishad Haider had become one of Pakistan's most successful banking professionals. Agha was looking after his farm. Asaf continued to work with the Pakistan Times, and chaired Pakistan's National Press Trust until he quit after a run-in with military leader Gen Zia ul-Haq. </p> 
<p>Amar was ensconced in the family's thriving new printing business in Delhi and Agra. </p> 
<p>They spoke of their joys and sorrows: the marriages of their children, the death of relatives. When Amar lost his family home in a posh Delhi neighbourhood due to a dispute with his brother, Agha wrote to him: </p> 
<p>"I was shocked and greatly distressed to hear about the sale of your house. I felt as if my own house had been sold. How very unfortunate that it had to come to this. But who knows. It might turn out to be good for you and the rest of the family." </p> 
<p>In January 1982, Amar returned to Pakistan to attend the wedding of Agha's son, Qasim. Since getting a visa required submitting the wedding card well in advance as proof, Agha got a special card made months in advance and sent it to his friend in Delhi. </p> 
<p>Since Amar only had a visa to visit Lahore, the others came to visit him from Karachi and Islamabad, where they were working. Over the next decade the Kapurs visited Pakistan three times, once availing of an easier visa given out to Indians to watch a rare cricket Test against their arch rivals. </p> 
<p>In Lahore, family members remember night-long conversations and days-long marathon contract bridge games when Amar came visiting. </p> 
<p>"They were like blood brothers, like a family. I found it interesting that all the four men were dynamic, successful individuals. But when they met they kind of merged into each other and became completely childlike. The intensity of friendship was something," Cyma Haider, daughter of Rishad Haider, told me. </p> 
<p>Amar would often pick up the phone and invite Agha to visit him in Delhi. One day, he wrote to him, saying he hoped to visit him soon. </p> 
<p>"Your repeated invitations to visit you all are so full of love and kindness that I feel very guilty in not having been able to make it so far. But sooner or later, Inshallah, we will and I hope in the not too distant future." </p> 
<p>As winter approached in 1988, Agha promised Amar that he would see him in Delhi in the new year. But in December, he collapsed in his home and died of a heart attack, aged 67. </p> 
<p>Rishad was the next to depart, in 1993, also aged 67. Feeling rather unwell, he was admitted to hospital a few days before his death, telling his family, "I think my time has come." </p> 
<p>In June 1996, an unusually despondent Asaf wrote to Amar: </p> 
<p>"How saddening is to lose lifelong friends. It is as if a part of you dies. Both Agha Ahmed and Rishad have left a void in my life, a void that can never be filled. I have myself been keeping indifferent health for some time now, and it may not be long before I join my departed friends in their eternal abode." </p> 
<p>"My only wish is that I should die as they died - suddenly and without lingering pain." </p> 
<p>Asaf wrote about "leading a lonely life, with both our children away in the US". He said they did meet on short visits to each other's countries every two to three years, but these "short visits only sharpen the sense of loneliness". </p> 
<p>"Sometimes I feel that life has become meaningless." </p> 
<p>Asaf contemplated a future where their children would continue the friendships forged by their parents. </p> 
<p>"If you and I cannot meet, let our children get together if they can and carry on a friendship which their fathers have been able to retain only flimsily due to a tragic quirk of history," Asaf wrote. </p> 
<p>A month later, on 29 July, Asaf Khwaja woke up in the morning, showered, had his breakfast and began reading the morning papers when he suffered a heart attack and died. He was 71. </p> 
<p>At 94, Amar Kapur is the only survivor of the brotherhood. He sold off his business some 20 years ago and continues to lead a busy life with his wife, Minna, in his two-storey home that he built in 1986 in Faridabad on the outskirts of Delhi. </p> 
<p>He is remarkably agile for his age, and lives with his pencil drawings, paintings, photographs and a boxful of memories. He is rather stoic about his past, taking more pride in his wife's work with the Rotary Club, than anything else. </p> 
<p>I ask whether he misses his old friends. </p> 
<p>"I miss them," he says. "I loved them and I love them even more now." </p> 
<p>"They are the only real friends I ever had." </p> 
<p>Pictures by Mansi Thapliyal. Archive pictures provided by family members. Interviews conducted in Delhi, and by phone to Karachi, Lahore, Islamabad and California. </p>
```

### 显示效果

<p>Seventy years ago, in August 1947, British colonial rule in India came to an end. The country was divided into two independent states - Hindu-majority India and Muslim-majority Pakistan. Using letters and diaries sourced from the world's first Partition Museum which opens in Amritsar on 17 August, Soutik Biswas pieces together the extraordinary story of four friends who were separated by the traumatic event and reunited 30 years later. </p> 
<p>"Our country has been broken; the great, sound pulsating heart of India has been broken," a young man in Lahore, Pakistan, wrote to his best friend in Delhi, the capital of India, in the summer of 1949. </p> 
<p>Writing in elegant cursive and turquoise blue ink, Asaf Khwaja had poured his heart out to Amar Kapur. Barely two years had passed since they had been separated by the bloody partition which split the subcontinent into the new independent nations of India and Pakistan. </p> 
<p>"We in Lahore, your friends and former playmates, those who were in school with you and in college and whose first 25 years of life, are inseparably linked with those of yours assure you with the utmost sincerity that distance has not made the slightest difference in our love and affection for you; that we remember you, and remembered you often, with the same brotherly feeling that for so long characterised our relations," wrote Asaf, who had just joined the Pakistan Times newspaper as a journalist. </p> 
<p>"We have spent good times, Amar, grand times, together." </p> 
<p>Amar Kapur, Asaf Khwaja, Agha Raza and Rishad Haider were like a brotherhood of friends. </p> 
<p>They lived within a three-mile radius, visited each other's homes, shared street snacks on the way home from convent school, studied in the same college and played soft ball cricket with twigs for stumps. From innocent boyhood to callow youth, they had shared the good times. </p> 
<p>Then, in the tumultuous summer of 1947, hard times arrived with a vengeance. </p> 
<p>Amar's separation had hurt the most. He was the only Hindu in the group, and his friends called him Punditji, which means a Hindu priest. </p> 
<p>Three weeks after the partition in August 1947, Amar and his family abandoned their sprawling family home and 57-year-old printing business in Lahore, and joined the millions of refugees that crossed the border in what was one of the greatest migrations in human history. </p> 
<p>Two years later, in Delhi, they were still trying to salvage their lives from the detritus of partition. </p> 
<p>Back in their severed homeland, Asaf, Agha and Rishad had entered adulthood and were starting to earn a living. </p> 
<p>Asaf's mordant wit was on magnificent display as he shared the news about their friends. </p> 
<p>"Agha and Rishad have entered into business - the swindlers. They are running an agency for Burmah Shell Company and minting a good bit of money. I wish you could see [Agha] Ahmad. He is (sic) grown so fat and bald that you would find it hard to recognise him - signs of prosperity!" Asaf wrote. </p> 
<p>Asaf was a pragmatic idealist. He loved cricket, poetry and the mountains and developed a love for contract bridge in his later life. He would sometimes spend his summers with his grandfather on a houseboat on Kashmir's Dal Lake or visit the unspoilt mountains of Swat. He was also hopeful about a brighter future for both nations. </p> 
<h2>Partition of India in August 1947 </h2> 
<ul> 
 <li>Perhaps the biggest movement of people in history, outside war and famine. </li> 
 <li>Two newly-independent states were created - India and Pakistan. </li> 
 <li>About 12 million people became refugees. Between half a million and a million people were killed in religious violence. </li> 
 <li>Tens of thousands of women were abducted. </li> 
 <li>This article is part of a BBC series looking at Partition 70 years on. </li> 
</ul> 
<p>Read more: </p> 
<p> Partition 70 years on: The turmoil, trauma - and legacy </p>       
<p>"Much suffering has been caused and much bitterness engendered," he wrote to Amar. "But what is done cannot be undone. All we can do now is to make amends for our past mistakes and work wholeheartedly for the restoration of peace and goodwill among the divided sections of the people." </p> 
<p>But Amar was less buoyant. Riots had broken out in Lahore - a Muslim-majority city where businesses were dominated by non-Muslims - in the months before partition. Under the smoke-filled skies, Hindus and Muslims had turned on each other, burnt down properties, and looted shops and homes. </p> 
<p>His father had forbidden the children and women in the house to step outside. When his family finally left Lahore in September in a convoy of cars, led by his father's grey Opel, he hid a .38 calibre revolver in the door lining. </p> 
<p>"It was madness, complete madness," Amar Kapur, now 94, told me recently. </p> 
<p>He kept a diary after migrating to Delhi following the blood-drenched summer of 1947 via the border city of Amritsar, where the family spent three months on the veranda of a house. In Delhi, the Kapurs then lived without electricity for three years in three rooms in a disputed house. </p> 
<p>"On 3 June 1947 it was decided that India would be partitioned and Pakistan would come into being. On that day was India doomed," Amar wrote in his diary. He wrote that violence hadn't stopped since the announcement. </p> 
<p>"Religion, which should be a strictly private affair and the concern of the individual, was being used to cover up beastly acts of murder and other inhuman acts," he wrote. </p> 
<p>Asaf, in Lahore, believed none of this would affect their friendship. "We have common memories and common experiences that bind us so closely together that no adventitious circumstances can wrench us apart," he wrote in one of his letters. </p> 
<p>But separated by distance, experience and time, the four friends did get separated. For three decades, they completely lost touch. Keeping friendships alive in rival, hostile nations was difficult, not least because it was hard to get visas to visit each other's countries. They also lost each other's addresses. </p> 
<ul> 
 <li> A Country Divided </li> 
 <li> How a jacket and a briefcase shaped a partition love story </li> 
 <li> Cyril Radcliffe: The man who drew the partition line </li> 
</ul> 
<p>A simple twist of fate brought the four together again, however. </p> 
<p>In the summer of 1980, an uncle of Agha Raza visited Delhi to attend a conference. Before he left, Agha had asked him to try trace Amar and his whereabouts. He told him that his family owned a printing press business in Delhi which bore the Kapur family name. </p> 
<p>Agha had been the maverick of the quartet. He had worked for an oil company, joined the Pakistani navy as an officer and then worked for the labour department. In his thirties, he retired to the countryside to look after his family farm, some 120km (74 miles) from Lahore. His friends called him the agriculturist. </p> 
<p>Now, he was on the hunt for his long-lost friend. </p> 
<p>In Delhi his uncle, a former diplomat, looked up the telephone directory and began calling all the Amar Kapurs. He got lucky with the fourth call, and returned to Pakistan with Amar's address and phone number. Soon the friends reconnected, speaking on the phone and writing to each other. </p> 
<p>They shared notes about themselves and their families - all of them were now married with children - and work. There was lot of catching up to do. </p> 
<p>Rishad Haider had become one of Pakistan's most successful banking professionals. Agha was looking after his farm. Asaf continued to work with the Pakistan Times, and chaired Pakistan's National Press Trust until he quit after a run-in with military leader Gen Zia ul-Haq. </p> 
<p>Amar was ensconced in the family's thriving new printing business in Delhi and Agra. </p> 
<p>They spoke of their joys and sorrows: the marriages of their children, the death of relatives. When Amar lost his family home in a posh Delhi neighbourhood due to a dispute with his brother, Agha wrote to him: </p> 
<p>"I was shocked and greatly distressed to hear about the sale of your house. I felt as if my own house had been sold. How very unfortunate that it had to come to this. But who knows. It might turn out to be good for you and the rest of the family." </p> 
<p>In January 1982, Amar returned to Pakistan to attend the wedding of Agha's son, Qasim. Since getting a visa required submitting the wedding card well in advance as proof, Agha got a special card made months in advance and sent it to his friend in Delhi. </p> 
<p>Since Amar only had a visa to visit Lahore, the others came to visit him from Karachi and Islamabad, where they were working. Over the next decade the Kapurs visited Pakistan three times, once availing of an easier visa given out to Indians to watch a rare cricket Test against their arch rivals. </p> 
<p>In Lahore, family members remember night-long conversations and days-long marathon contract bridge games when Amar came visiting. </p> 
<p>"They were like blood brothers, like a family. I found it interesting that all the four men were dynamic, successful individuals. But when they met they kind of merged into each other and became completely childlike. The intensity of friendship was something," Cyma Haider, daughter of Rishad Haider, told me. </p> 
<p>Amar would often pick up the phone and invite Agha to visit him in Delhi. One day, he wrote to him, saying he hoped to visit him soon. </p> 
<p>"Your repeated invitations to visit you all are so full of love and kindness that I feel very guilty in not having been able to make it so far. But sooner or later, Inshallah, we will and I hope in the not too distant future." </p> 
<p>As winter approached in 1988, Agha promised Amar that he would see him in Delhi in the new year. But in December, he collapsed in his home and died of a heart attack, aged 67. </p> 
<p>Rishad was the next to depart, in 1993, also aged 67. Feeling rather unwell, he was admitted to hospital a few days before his death, telling his family, "I think my time has come." </p> 
<p>In June 1996, an unusually despondent Asaf wrote to Amar: </p> 
<p>"How saddening is to lose lifelong friends. It is as if a part of you dies. Both Agha Ahmed and Rishad have left a void in my life, a void that can never be filled. I have myself been keeping indifferent health for some time now, and it may not be long before I join my departed friends in their eternal abode." </p> 
<p>"My only wish is that I should die as they died - suddenly and without lingering pain." </p> 
<p>Asaf wrote about "leading a lonely life, with both our children away in the US". He said they did meet on short visits to each other's countries every two to three years, but these "short visits only sharpen the sense of loneliness". </p> 
<p>"Sometimes I feel that life has become meaningless." </p> 
<p>Asaf contemplated a future where their children would continue the friendships forged by their parents. </p> 
<p>"If you and I cannot meet, let our children get together if they can and carry on a friendship which their fathers have been able to retain only flimsily due to a tragic quirk of history," Asaf wrote. </p> 
<p>A month later, on 29 July, Asaf Khwaja woke up in the morning, showered, had his breakfast and began reading the morning papers when he suffered a heart attack and died. He was 71. </p> 
<p>At 94, Amar Kapur is the only survivor of the brotherhood. He sold off his business some 20 years ago and continues to lead a busy life with his wife, Minna, in his two-storey home that he built in 1986 in Faridabad on the outskirts of Delhi. </p> 
<p>He is remarkably agile for his age, and lives with his pencil drawings, paintings, photographs and a boxful of memories. He is rather stoic about his past, taking more pride in his wife's work with the Rotary Club, than anything else. </p> 
<p>I ask whether he misses his old friends. </p> 
<p>"I miss them," he says. "I loved them and I love them even more now." </p> 
<p>"They are the only real friends I ever had." </p> 
<p>Pictures by Mansi Thapliyal. Archive pictures provided by family members. Interviews conducted in Delhi, and by phone to Karachi, Lahore, Islamabad and California. </p>


------


## 本系统的缺陷

HTML 转 Json 的部分代码,较为粗暴, 可能有位置的bug