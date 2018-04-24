# NameInitialsCircleImageView
A circle image view library that not only supports showing circular images (also loads them using Picasso) but also supports showing name initial letters with a background color in case there is no image available or image loading failed in case there was some issue with internet or url.

![screenshot-1.png](demo-images/s1.png) 

It is just a custom view and you can use it just like you use any native Android views. You can also specify custom fonts just like any other android view.

## Download

```
implementation 'io.github.informramiz:nameinitialscircleimageview:1.2'
```

## Getting Started

**XML**

```
<com.github.ramiz.nameinitialscircleimageview.NameInitialsCircleImageView
        android:id="@+id/initialsCircleImageView"
        android:layout_width="90dp"
        android:layout_height="90dp"
        android:layout_marginTop="50dp"
        app:circleBackgroundColor="@color/colorAccent"
        app:textFont="@font/aguafina_script"
        app:text="RR"
        app:textSize="20sp"/>
```

**Java**

```
String imageUrl = "http://i.imgur.com/DvpvklR.png";
NameInitialsCircleImageView.ImageInfo imageInfo = new NameInitialsCircleImageView.ImageInfo
                .Builder("RR")
                .setTextColor(android.R.color.primary_text_dark)
                .setTextFont(R.font.aguafina_script)
                .setImageUrl(imageUrl)
                .setCircleBackgroundColorRes(android.R.color.holo_orange_dark)
                .build();
initialsCircleImageView.setImageInfo(imageInfo);
```

**Kotlin**

```
val imageUrl = "http://i.imgur.com/DvpvklR.png"
        //image info config with valid image url so this should display image
val imageInfo: NameInitialsCircleImageView.ImageInfo = NameInitialsCircleImageView.ImageInfo
                .Builder("RR")
                .setTextColor(android.R.color.primary_text_dark)
                .setTextFont(R.font.aguafina_script)
                .setImageUrl(imageUrl)
                .setCircleBackgroundColorRes(android.R.color.holo_orange_dark)
                .build()
initialsCircleImageView.setImageInfo(imageInfo)
```

**Using a Color Generator**

By default, if you will not specify a background fill color then library will use a default color generator. 

- If you want to specify your own colors so that they fit your app theme then you can easily do so by passing your colors to default color generator instance. For example,

```
//initialize your colors
val yourColors: List<Int> = ...

//pass the color generator along with other info 
val imageInfo: NameInitialsCircleImageView.ImageInfo = NameInitialsCircleImageView.ImageInfo
                .Builder("Raja")
                .setTextFont(R.font.aguafina_script)
                .setColorGenerator(MaterialColorGenerator(yourColors))
                .build()
initialsCircleImageView.setImageInfo(imageInfo)
```

- If you want to control how colors are generated based on text then you can do so by providing a custom implementation of `ColorGenerator` interface.

```
class YourColorGenerator : ColorGenerator {
	fun generateColor(key: Any): Int {
		//your custom implementatoin goes here
	}
}


//pass the custom color generator along with other info
val imageInfo: NameInitialsCircleImageView.ImageInfo = NameInitialsCircleImageView.ImageInfo
                .Builder("Raja")
                .setTextFont(R.font.aguafina_script)
                .setColorGenerator(YourColorGenerator())
                .build()
initialsCircleImageView.setImageInfo(imageInfo)
```

