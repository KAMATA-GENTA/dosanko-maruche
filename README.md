# どさん娘　マルシェ
## Overview

## Getting Started
### javaが見えているか確認する
```bash
java --version
```
見えていない場合
eclipseを使っていて、javaが見つからない場合、
以下のコマンドを実行してから上記のコマンドを実行してください。
各々のjavaのpathを入れてください
```bash
export JAVA_HOME="/c/pleiades/2025-12/java/21"
export PATH="$JAVA_HOME/bin:$PATH"
```
### installする
プロジェクトルートで以下のコマンドを実行してください
```bash
./mvnw generate-resources
```
### tailwincssをwatchする
frontendディレクトリに移動して
```bash
export PATH="$(pwd)/node:$PATH"
npm run tailwind-watch
```


### フォルダ構成
### DBスキーマ
### テーブル定義

### users（ユーザー）

|論理名|物理名|型|制約|説明|
|---|---|---|---|---|
|ID|id|SERIAL|PK|ユーザー識別ID|
|ユーザー名|username|VARCHAR(100)||表示名|
|メールアドレス|email|VARCHAR(255)||ログイン用|
|パスワード|password_hash|VARCHAR(255)||暗号化パスワード|

---

### products（商品）

|論理名|物理名|型|制約|説明|
|---|---|---|---|---|
|ID|id|SERIAL|PK|商品識別ID|
|地域ID|region_id|INT||Java側地域ID|
|カテゴリID|category_id|INT||Java側カテゴリID|
|商品名|product_name|VARCHAR(255)||商品名|
|価格|price|INT||商品価格|
|商品説明|description|TEXT||説明|
|画像URL|image_url|VARCHAR(255)||商品画像|

---

### reviews（レビュー）

|論理名|物理名|型|制約|説明|
|---|---|---|---|---|
|ID|id|SERIAL|PK|レビュー識別ID|
|商品ID|product_id|INT|FK|対象商品|
|ユーザーID|user_id|INT|FK|投稿者|
|評価|rating|INT||1〜5|
|コメント|comment|TEXT||レビュー本文|
|投稿日|created_at|TIMESTAMP||投稿日時|

---

### cart_items（カート）

|論理名|物理名|型|制約|説明|
|---|---|---|---|---|
|ID|id|SERIAL|PK|カート識別ID|
|ユーザーID|user_id|INT|FK|所有者|
|商品ID|product_id|INT|FK|商品|
|数量|quantity|INT||購入数|

---

### orders（注文）

|論理名|物理名|型|制約|説明|
|---|---|---|---|---|
|ID|id|SERIAL|PK|注文番号|
|ユーザーID|user_id|INT|FK|購入者|
|商品合計|subtotal|INT||送料抜き|
|送料|shipping_fee|INT||送料|
|注文日時|ordered_at|TIMESTAMP||購入日時|

---

### order_details（注文詳細）

|論理名|物理名|型|制約|説明|
|---|---|---|---|---|
|ID|id|SERIAL|PK|明細ID|
|注文ID|order_id|INT|FK|注文番号|
|商品ID|product_id|INT|FK|商品|
|数量|quantity|INT||購入数|
|購入時価格|price|INT||購入時価格|

---

### テーブル関係

|親テーブル|子テーブル|関係|意味|
|---|---|---|---|
|users|reviews|1対多|ユーザーは複数レビューを書ける|
|users|cart_items|1対多|ユーザーは複数商品をカートに入れられる|
|users|orders|1対多|ユーザーは複数回注文できる|
|products|reviews|1対多|商品には複数レビューが付く|
|products|cart_items|1対多|商品は複数ユーザーのカートに入る|
|products|order_details|1対多|商品は複数回注文される|
|orders|order_details|1対多|1つの注文に複数商品を含められる|
