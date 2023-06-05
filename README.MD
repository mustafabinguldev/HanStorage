```

	<repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
	</repository>

	
	<dependency>
	    <groupId>com.github.BingulHan</groupId>
	    <artifactId>HanStorage</artifactId>
	    <version>0.5</version>
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
        
        //You can save all data in a file with JSON format.
        converter.write("C:\\Users\\BingulHan\\Desktop\\HanStorage\\src\\main\\resources\\", "backups", storage);
        
        You can read the data in JSON format and convert it as a storage object. (Note: It works correctly if the data is kept as key and value.)
        IReader reader = new JSONReader();
        Optional<HanStorage> hanStorage = reader.read("C:\\Users\\BingulHan\\Desktop\\HanStorage\\src\\main\\resources\\backups.json");
       
        
```
