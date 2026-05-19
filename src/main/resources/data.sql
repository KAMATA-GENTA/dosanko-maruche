--usersテーブル　ここにデータいれとかないと動かなくなるもの多いので、デモでデータの挿入をしていますが、最終的には消します
INSERT INTO users (
    username,
    email,
    password_hash
)
VALUES
(
    'tanaka',
    'tanaka@example.com',
    'password123'
),
(
    'sato',
    'sato@example.com',
    'password456'
);

-- products 初期データ

INSERT INTO products
(region_id, category_id, product_name, price, description, image_url)
VALUES

-- =========================
-- 札幌
-- =========================
(1, 1, '札幌市場直送 いくら醤油漬け', 3980, '札幌中央卸売市場をイメージした濃厚ないくら醤油漬け。','/images/region-product/sapporo/s-ikura.jpg'),
(1, 1, '札幌海鮮ほたてセット', 3280, '甘みのあるほたてを食べやすいセットにしました。', '/images/region-product/sapporo/s-hotate.jpg'),
(1, 2, '札幌近郊じゃがいもセット', 1980, '北海道産じゃがいもを詰め合わせました。', '/images/region-product/sapporo/s-jagaimo.jpg'),
(1, 2, '札幌とうもろこし詰め合わせ', 2480, '甘みの強いとうもろこしセット。', '/images/region-product/sapporo/s-toumorokosi.jpg'),
(1, 3, '札幌味噌だれ豚肉セット', 2980, '札幌味噌をイメージした味付け豚肉。', '/images/region-product/sapporo/s-butaniku.jpg'),
(1, 3, '札幌牛乳仕込みチーズ', 1780, '北海道産牛乳を使用したまろやかチーズ。', '/images/region-product/sapporo/s-tizu.jpg'),
(1, 4, '白い恋人風 ラングドシャ', 1680, '北海道土産をイメージした人気焼き菓子。', '/images/region-product/sapporo/s-rangudosya.jpg'),
(1, 4, '札幌味噌ラーメンセット', 2200, 'コク深い味噌スープが特徴のラーメン。', '/images/region-product/sapporo/s-misoramen.jpg'),
(1, 4, '札幌スープカレーセット', 2600, 'スパイス香る札幌名物スープカレー。', '/images/region-product/sapporo/s-supukare.jpg'),
(1, 2, '札幌アスパラガス', 2300, 'シャキシャキ食感の北海道産アスパラ。', '/images/region-product/sapporo/s-asuparagasu.jpg'),

-- =========================
-- 函館
-- =========================
(2, 1, '函館真昆布', 1800, '上品なだしが特徴の函館産昆布。', '/images/region-product/hakodate/h-konbu.jpg'),
(2, 1, '函館いか一夜干し', 2500, '函館名物のいかを使用した一夜干し。', '/images/region-product/hakodate/h-ika.jpg'),
(2, 1, '函館ほたて貝柱', 3600, '甘みの強いほたて貝柱セット。', '/images/region-product/hakodate/h-kaibasira.jpg'),
(2, 2, '函館近郊にんじんセット', 1900, '甘みのあるにんじんを詰め合わせました。', '/images/region-product/hakodate/h-ninjin.jpg'),
(2, 2, '函館だいこんセット', 2100, '煮物や漬物に使いやすいだいこんセット。', '/images/region-product/hakodate/h-daikon.jpg'),
(2, 3, '函館牛乳プリン', 1600, '北海道牛乳を使用した濃厚プリン。', '/images/region-product/hakodate/h-purin.jpg'),
(2, 3, '函館チーズセット', 2100, 'ワインにも合うチーズセット。', '/images/region-product/hakodate/h-tizu.jpg'),
(2, 4, '函館塩ラーメンセット', 2200, 'あっさり塩味が人気の函館ラーメン。', '/images/region-product/hakodate/h-sioramen.jpg'),
(2, 4, '函館バターサンド', 1800, '北海道バターを使った焼き菓子。', '/images/region-product/hakodate/h-batasando.jpg'),
(2, 4, '函館夜景チョコクッキー', 1500, '函館夜景をイメージしたチョコクッキー。', '/images/region-product/hakodate/h-cokkie.jpg'),

-- =========================
-- 北見
-- =========================
(3, 1, '北見ほたて干し貝柱', 4200, '旨みが凝縮された干し貝柱。', '/images/region-product/kitami/k-kaibasira.jpg'),
(3, 1, '北見昆布だしセット', 1700, '料理に便利な昆布だしセット。', '/images/region-product/kitami/k-konbudasi.jpg'),
(3, 2, '北見たまねぎ', 1600, '北見名産の甘みあるたまねぎ。', '/images/region-product/kitami/k-tamanegi.jpeg'),
(3, 2, '北見白花豆', 1900, '煮豆やスイーツにも使える白花豆。', '/images/region-product/kitami/k-sirohanamame.jpg'),
(3, 2, '北見ビート野菜セット', 2300, '北海道らしい甜菜をイメージした野菜セット。', '/images/region-product/kitami/k-bito.jpg'),
(3, 3, '北見牛焼肉セット', 4800, '旨みたっぷりの牛焼肉セット。', '/images/region-product/kitami/k-yakiniku.jpg'),
(3, 3, '北見豚ジンギスカン', 3200, '特製だれで味付けした豚肉。', '/images/region-product/kitami/k-jingisukan.jpg'),
(3, 4, '北見ハッカ飴', 900, '爽やかなハッカ風味の飴。', '/images/region-product/kitami/k-hakkaame.jpg'),
(3, 4, '北見ハッカチョコ', 1200, 'ハッカとチョコを組み合わせた人気菓子。', '/images/region-product/kitami/k-hakkathoko.jpg'),
(3, 4, '北見オニオンスープ', 1400, '北見産たまねぎをイメージしたスープ。', '/images/region-product/kitami/k-onionsupu.jpg'),

-- =========================
-- 稚内
-- =========================
(4, 1, '稚内毛ガニ', 7800, '濃厚な旨みが特徴の毛ガニ。', '/images/region-product/wakkanai/w-kegani.jpg'),
(4, 1, '稚内利尻昆布', 2600, '高級だし昆布として人気。', '/images/region-product/wakkanai/w-konbu.jpg'),
(4, 1, '稚内ほたてセット', 3500, '甘みあるほたての詰め合わせ。', '/images/region-product/wakkanai/w-hotate.jpg'),
(4, 2, '稚内かぼちゃ', 2000, '甘みがあり料理に使いやすいかぼちゃ。', '/images/region-product/wakkanai/w-kabotya.jpg'),
(4, 2, '稚内寒じめほうれん草', 2400, '寒暖差で甘みを増したほうれん草。', '/images/region-product/wakkanai/w-hourensou.jpg'),
(4, 3, '稚内牛乳', 1300, 'コク深い北海道牛乳。', '/images/region-product/wakkanai/w-gyunyu.jpg'),
(4, 3, '稚内チーズセット', 2300, '濃厚ミルクを使用したチーズ。', '/images/region-product/wakkanai/w-tizu.jpg'),
(4, 4, '稚内昆布ラーメン', 2100, '昆布だしを効かせたラーメン。', '/images/region-product/wakkanai/w-ramen.jpg'),
(4, 4, '稚内ミルククッキー', 1500, '牛乳風味豊かなクッキー。', '/images/region-product/wakkanai/w-milkcokkie.jpg'),
(4, 4, '稚内ポテマルコ', 1600, '稚内ブランド認定の「勇知いも」を皮まで丸ごと練り込んだ、ほろほろ食感のじゃがいもクッキー', '/images/region-product/wakkanai/w-potemorukp.jpg'),

-- =========================
-- 小樽
-- =========================
(5, 1, '小樽海鮮丼セット', 4600, '小樽港をイメージした海鮮セット。', '/images/region-product/otaru/ot-otarukaisenndonset.jpg'),
(5, 1, '小樽いくら醤油漬け', 3900, '濃厚ないくら醤油漬け。', '/images/region-product/otaru/ot-ikurasyouyuduke.jpg'),
(5, 1, '小樽ほたてバター焼きセット', 3400, 'バター焼き向けほたてセット。', '/images/region-product/otaru/ot-hotatebutteryakiset.jpg'),
(5, 2, '小樽メロン', 3200, '北海道らしい甘みを楽しめるメロン。', '/images/region-product/otaru/ot-otarumeron.jpg'),
(5, 3, '小樽チーズケーキ', 2200, '北海道産チーズを使った濃厚ケーキ。', '/images/region-product/otaru/ot-otarucheesecake.jpg'),
(5, 3, '小樽牛乳プリン', 1600, 'なめらかな口当たりのプリン。', '/images/region-product/otaru/ot-otarumilkpudding.jpg'),
(5, 4, '小樽ガラス風キャンディ', 1000, '小樽ガラスをイメージした飴。', '/images/region-product/otaru/ot-otarugalascandy.jpg'),
(5, 4, '小樽バタークッキー', 1500, '香り豊かなバタークッキー。', '/images/region-product/otaru/ot-buttercookier.jpg'),
(5, 4, '小樽海鮮ラーメン', 2300, '魚介だしが効いたラーメン。', '/images/region-product/otaru/ot-seafoodra-men.jpg'),

-- =========================
-- 帯広
-- =========================
(6, 1, '帯広昆布だしセット', 1500, '料理に便利な昆布だし。', '/images/reigon-product/obihiro/ob-koknbudasiset.jpg'),
(6, 1, '帯広ほたて加工品セット', 3000, 'ほたて加工品を詰め合わせました。', '/images/reigon-product/obihiro/ob-hotatekakouhin.jpg'),
(6, 2, '帯広長いも', 2200, '粘りがあり、とろろにも向いた長いも。', '/images/reigon-product/obihiro/ob-obihironagaimo.jpg'),
(6, 2, '帯広小豆', 1800, '和菓子やあんこ作りに使える小豆。', '/images/reigon-product/obihiro/ob-obihiroazuki.jpg'),
(6, 2, '帯広枝豆セット', 2400, '香りと甘みの強い枝豆セット。', '/images/reigon-product/obihiro/ob-obihiroedamame.jpg'),
(6, 3, '帯広豚丼セット', 3600, '帯広名物豚丼用の味付け豚肉。', '/images/reigon-product/obihiro/ob-obihirobutadonn.jpg'),
(6, 3, '十勝牛ステーキ', 5800, '十勝地方をイメージした牛ステーキ。', '/images/reigon-product/obihiro/ob-tokatigyu.jpg'),
(6, 3, '帯広チーズセット', 2300, '乳製品を活かした濃厚チーズ。', '/images/reigon-product/obihiro/ob-obihirocheeseset.jpg'),
(6, 4, '帯広バターサンド', 1800, '濃厚バタークリーム入り焼き菓子。', '/images/reigon-product/obihiro/ob-obihirobuttersand.jpg'),
(6, 4, '帯広ミルクキャラメル', 1200, '牛乳のコクを感じるキャラメル。', '/images/reigon-product/obihiro/ob-milkkyatameru.jpg');




-- ===== 注文データ =====
INSERT INTO orders (id, user_id, subtotal, shipping_fee, ordered_at) VALUES
(1, 1, 15920, 500,  '2024-01-15 10:30:00'), -- 注文1: いくら(3980円) × 4個 = 15920円
(2, 1, 1800,  800,  '2024-02-20 14:00:00'), -- 注文2: 昆布(1800円) × 1個 = 1800円
(3, 1, 4200,  500,  '2024-03-05 09:15:00'), -- 注文3: 貝柱(4200円) × 1個 = 4200円
(4, 1, 7800,  500,  '2024-04-10 16:45:00'), -- 注文4: 毛ガニ(7800円) × 1個 = 7800円
(5, 1, 4600,  600,  '2024-05-01 11:00:00'); -- 注文5: 海鮮丼(4600円) × 1個 = 4600円

-- ===== 注文明細データ =====
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES
(1, 1,  4, 3980),   -- 札幌(地域1)
(2, 11, 1, 1800),   -- 函館(地域2)
(3, 21, 1, 4200),   -- 北見(地域3)
(4, 31, 1, 7800),   -- 稚内(地域4)
(5, 41, 1, 4600);   -- 小樽(地域5)



-- ==========================================
-- ユーザーID: 2 (satoさん) の注文データ
-- ==========================================
-- 注文ID（id）は、tanakaさんのデータ(1〜5)と被らないように「6」と「7」から始めます。
-- user_id に「2」を指定します。
INSERT INTO orders (id, user_id, subtotal, shipping_fee, ordered_at) VALUES
(6, 2, 6800, 500, '2024-05-10 12:00:00'), -- 注文6: 合計 6800円 (1600円×2 + 3600円)
(7, 2, 2600, 500, '2024-05-18 18:30:00'); -- 注文7: 合計 2600円 (2600円×1)

-- ==========================================
-- ユーザーID: 2 (satoさん) の注文明細データ
-- ==========================================
INSERT INTO order_details (order_id, product_id, quantity, price) VALUES
-- 【注文ID: 6 の明細】
(6, 23, 2, 1600),   -- 北見たまねぎ (商品ID: 23) × 2個
(6, 55, 1, 3600),   -- 帯広豚丼セット (商品ID: 55) × 1個

-- 【注文ID: 7 の明細】
(7, 9,  1, 2600);   -- 札幌スープカレーセット (商品ID: 9) × 1個

