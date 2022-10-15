```

	<repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
	</repository>

	
	<dependency>
	    <groupId>com.github.BingulHan</groupId>
	    <artifactId>HanStorage</artifactId>
	    <version>0.3</version>
	</dependency>
	
```

```

        //Automatic file is created
        HanStorage storage = new HanStorage("C:\\Users\\BingulHan\\Desktop\\HanStorage\\src\\main\\resources", "example");

        //How to add data
        storage.addData(new HanData().setKey("Github").setValue("bingulhan"));

        //How to call data
        if (storage.getData("github").isPresent()) {
            HanData d = storage.getData("github").get();
            System.out.println("Key: "+d.getKey());
            System.out.println("Value: "+d.getValue());
            System.out.println("Line: "+d.getLine());
        }

        //How to save file
        storage.save();

        //List data
        storage.list();


        //How to delete data
        storage.remove("github");
        storage.save();

        /*Auto saves after deletion
        storage.removeSave("github");*/
        
        //How can it be converted to other data types
        IConverter converter = new JSONConverter();
        JSONObject jsonObject = (JSONObject) converter.convert(students);
        System.out.println(""+jsonObject.toString());
        
       
        
```
