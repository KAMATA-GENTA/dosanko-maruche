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
(1, 1, '札幌市場直送 いくら醤油漬け', 3980, '北海道産の濃厚いくら醤油漬け。','/images/reigon-product/sapporo/s-ikura.jpg'),
(1, 1, '海鮮ほたてセット', 3280, '甘みのあるほたて詰め合わせセット。', '/images/reigon-product/sapporo/s-hotate.jpg'),
(1, 2, 'じゃがいもセット', 1980, '北海道産じゃがいも詰め合わせセット。', '/images/reigon-product/sapporo/s-jagaimo.jpg'),
(1, 2, 'とうもろこし詰め合わせ', 2480, '甘みの強いとうもろこしセット。', '/images/reigon-product/sapporo/s-toumorokosi.jpg'),
(1, 3, '味噌だれ豚肉セット', 2980, '札幌味噌をイメージした味付け豚肉。', '/images/reigon-product/sapporo/s-butaniku.jpg'),
(1, 3, '牛乳仕込みチーズ', 1780, '北海道産牛乳を使用したまろやかチーズ。', '/images/reigon-product/sapporo/s-tizu.jpg'),
(1, 4, '白い恋人 ラングドシャ', 1680, '北海道土産をイメージした人気焼き菓子。', '/images/reigon-product/sapporo/s-rangudosya.jpg'),
(1, 4, '札幌味噌ラーメンセット', 2200, 'コク深い味噌スープが特徴の札幌ラーメン。', '/images/reigon-product/sapporo/s-misoramen.jpg'),
(1, 4, 'スープカレーセット', 2600, 'スパイス香る札幌名物スープカレー。', '/images/reigon-product/sapporo/s-supukare.jpg'),
(1, 2, 'アスパラガス', 2300, 'シャキシャキ食感の北海道産アスパラ。', '/images/reigon-product/sapporo/s-asuparagasu.jpg'),

-- =========================
-- 函館
-- =========================
(2, 1, '真昆布', 1800, '上品なだしが特徴の函館産昆布。', '/images/reigon-product/hakodate/h-konbu.jpg'),
(2, 1, 'いか一夜干し', 2500, '函館名物のいかを使用した一夜干し。', '/images/reigon-product/hakodate/h-ika.jpg'),
(2, 1, 'ほたて貝柱', 3600, '甘みの強いほたて貝柱セット。', '/images/reigon-product/hakodate/h-kaibasira.jpg'),
(2, 2, 'にんじんセット', 1900, '甘みのあるにんじんを詰め合わせセット。', '/images/reigon-product/hakodate/h-ninjin.jpg'),
(2, 2, 'だいこんセット', 2100, '煮物や漬物に使いやすいだいこんセット。', '/images/reigon-product/hakodate/h-daikon.jpg'),
(2, 3, '牛乳プリン', 1600, '北海道牛乳を使用した濃厚プリン。', '/images/reigon-product/hakodate/h-purin.jpg'),
(2, 3, 'チーズセット', 2100, 'ワインにも合うチーズセット。', '/images/reigon-product/hakodate/h-tizu.jpg'),
(2, 4, '塩ラーメンセット', 2200, 'あっさり塩味が人気の函館ラーメン。', '/images/reigon-product/hakodate/h-sioramen.jpg'),
(2, 4, 'バターサンド', 1800, '北海道バターを使った焼き菓子。', '/images/reigon-product/hakodate/h-batasando.jpg'),
(2, 4, '夜景チョコクッキー', 1500, '函館夜景をイメージしたチョコクッキー。', '/images/reigon-product/hakodate/h-cokkie.jpg'),

-- =========================
-- 北見
-- =========================
(3, 1, 'ほたて干し貝柱', 4200, '旨みが凝縮された干し貝柱。', '/images/reigon-product/kitami/k-kaibasira.jpg'),
(3, 1, '北見昆布だしセット', 1700, '料理に便利な昆布だしセット。', '/images/reigon-product/kitami/k-konbudasi.jpg'),
(3, 2, 'たまねぎ', 1600, '北見名産の甘みあるたまねぎ。', '/images/reigon-product/kitami/k-tamanegi.jpeg'),
(3, 2, '白花豆', 1900, '煮豆やスイーツにも使える白花豆。', '/images/reigon-product/kitami/k-sirohanamame.jpg'),
(3, 2, 'ビート野菜セット', 2300, '北海道らしい甜菜をイメージした野菜セット。', '/images/reigon-product/kitami/k-bito.jpg'),
(3, 3, '牛焼肉セット', 4800, '旨みたっぷりの牛焼肉セット。', '/images/reigon-product/kitami/k-yakiniku.jpg'),
(3, 3, '豚ジンギスカン', 3200, '特製だれで味付けした豚肉。', '/images/reigon-product/kitami/k-jingisukan.jpg'),
(3, 4, 'ハッカ飴', 900, '爽やかなハッカ風味の飴。', '/images/reigon-product/kitami/k-hakkaame.jpg'),
(3, 4, 'ハッカチョコ', 1200, 'ハッカとチョコを組み合わせた人気菓子。', '/images/reigon-product/kitami/k-hakkathoko.jpg'),
(3, 4, 'オニオンスープ', 1400, '北見産たまねぎをイメージしたスープ。', '/images/reigon-product/kitami/k-onionsupu.jpg'),

-- =========================
-- 稚内
-- =========================
(4, 1, '毛ガニ', 7800, '濃厚な旨みが特徴の毛ガニ。', '/images/reigon-product/wakkanai/w-kegani.jpg'),
(4, 1, '利尻昆布', 2600, '高級だし昆布として人気。', '/images/reigon-product/wakkanai/w-konbu.jpg'),
(4, 1, 'ほたてセット', 3500, '甘みあるほたての詰め合わせ。', '/images/reigon-product/wakkanai/w-hotate.jpg'),
(4, 2, 'かぼちゃ', 2000, '甘みがあり料理に使いやすいかぼちゃ。', '/images/reigon-product/wakkanai/w-kabotya.jpg'),
(4, 2, '寒じめほうれん草', 2400, '寒暖差で甘みを増したほうれん草。', '/images/reigon-product/wakkanai/w-hourensou.jpg'),
(4, 3, '稚内牛乳', 1300, 'コク深い稚内産の牛乳。', '/images/reigon-product/wakkanai/w-gyunyu.jpg'),
(4, 3, 'チーズセット', 2300, '濃厚ミルクを使用したチーズ。', '/images/reigon-product/wakkanai/w-tizu.jpg'),
(4, 4, '昆布ラーメン', 2100, '昆布だしを効かせたラーメン。', '/images/reigon-product/wakkanai/w-ramen.jpg'),
(4, 4, 'ミルククッキー', 1500, '牛乳風味豊かなクッキー。', '/images/reigon-product/wakkanai/w-milkcokkie.jpg'),
(4, 4, 'ポテマルコ', 1600, '稚内ブランド認定の「勇知いも」を使用したじゃがいもクッキー', '/images/reigon-product/wakkanai/w-potemorukp.jpg'),

-- =========================
-- 小樽
-- =========================
(5, 1, '海鮮丼セット', 4600, '小樽港をイメージした海鮮セット。', '/images/reigon-product/otaru/ot-otarukaisenndonset.jpg'),
(5, 1, '小樽いくら醤油漬け', 3900, '濃厚ないくら醤油漬け。', '/images/reigon-product/otaru/ot-ikurasyouyuduke.jpg'),
(5, 1, 'ほたてバター焼きセット', 3400, 'バター焼き向けほたてセット。', '/images/reigon-product/otaru/ot-hotatebutteryakiset.jpg'),
(5, 2, '小樽メロン', 3200, '北海道らしい甘みを楽しめるメロン。', '/images/reigon-product/otaru/ot-otarumeron.jpg'),
(5, 3, 'チーズケーキ', 2200, '北海道産チーズを使った濃厚ケーキ。', '/images/reigon-product/otaru/ot-otarucheesecake.jpg'),
(5, 3, '牛乳プリン', 1600, 'なめらかな口当たりのプリン。', '/images/reigon-product/otaru/ot-otarumilkpudding.jpg'),
(5, 4, 'ガラス風キャンディ', 1000, '小樽ガラスをイメージした飴。', '/images/reigon-product/otaru/ot-otarugalascandy.jpg'),
(5, 4, 'バタークッキー', 1500, '香り豊かなバタークッキー。', '/images/reigon-product/otaru/ot-buttercookier.jpg'),
(5, 4, '海鮮ラーメン', 2300, '魚介だしが効いたラーメン。', '/images/reigon-product/otaru/ot-seafoodra-men.jpg'),

-- =========================
-- 帯広
-- =========================
(6, 1, '帯広昆布だしセット', 1500, '料理に便利な昆布だし。', '/images/reigon-product/obihiro/ob-koknbudasiset.jpg'),
(6, 1, 'ほたて加工品セット', 3000, 'ほたて加工品を詰め合わせました。', '/images/reigon-product/obihiro/ob-hotatekakouhin.jpg'),
(6, 2, '長いも', 2200, '粘りがあり、とろろにも向いた長いも。', '/images/reigon-product/obihiro/ob-obihironagaimo.jpg'),
(6, 2, '小豆', 1800, '和菓子やあんこ作りに使える小豆。', '/images/reigon-product/obihiro/ob-obihiroazuki.jpg'),
(6, 2, '枝豆セット', 2400, '香りと甘みの強い枝豆セット。', '/images/reigon-product/obihiro/ob-obihiroedamame.jpg'),
(6, 3, '豚丼セット', 3600, '帯広名物豚丼用の味付け豚肉。', '/images/reigon-product/obihiro/ob-obihirobutadonn.jpg'),
(6, 3, '十勝牛ステーキ', 5800, '十勝地方をイメージした牛ステーキ。', '/images/reigon-product/obihiro/ob-tokatigyu.jpg'),
(6, 3, 'チーズセット', 2300, '乳製品を活かした濃厚チーズ。', '/images/reigon-product/obihiro/ob-obihirocheeseset.jpg'),
(6, 4, 'バターサンド', 1800, '濃厚バタークリーム入り焼き菓子。', '/images/reigon-product/obihiro/ob-obihirobuttersand.jpg'),
(6, 4, 'ミルクキャラメル', 1200, '牛乳のコクを感じるキャラメル。', '/images/reigon-product/obihiro/ob-milkkyatameru.jpg');
