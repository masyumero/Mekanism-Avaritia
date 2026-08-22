from PIL import Image
import glob

for file in glob.glob("*.png"):
    img = Image.open(file).convert("RGBA")
    data = img.getdata()

    new_data = []
    # 置き換え前の色 (R, G, B, A) と 置き換え後の色 (R, G, B, A)
    target_color = (0, 0, 0, 0)  # 例: 不透明な白
    replace_color = (96, 96, 96, 255)  # 例: 不透明な赤に置き換え

    for item in data:
      # 指定した色に一致するか判定
      if item == target_color:
        new_data.append(replace_color)
      else:
        new_data.append(item)

    # 新しいデータを画像に戻して保存
    img.putdata(new_data)
    img.save("opaque/" + file)