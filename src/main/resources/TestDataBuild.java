import java.util.ArrayList;
import java.util.List;

import com.demo.pojo.AddPlace;
import com.demo.pojo.Location;

public class TestDataBuild {

	 public AddPlace addPlaceAPI() {
		 AddPlace addPlace = new AddPlace();
			Location location=new Location();
			location.setLat(-38.383494);
			location.setLng(33.427362);
			addPlace.setAccuracy(50);
			addPlace.setAddress("29, side layout, cohen 09");
			addPlace.setLanguage("French-IN");
			addPlace.setWebsite("http://rahulshettyacademy.com");
			addPlace.setPhone_number("(+91) 983 893 3937");
			addPlace.setName("Rahul Shetty Academy");
			List<String> list=new ArrayList<String>();
			list.add("shoe park");
			list.add("shop");
			addPlace.setTypes(list);
			addPlace.setLocation(location);
			return addPlace;
	 }
	
   	
}
 