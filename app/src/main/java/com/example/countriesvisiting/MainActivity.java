package com.example.countriesvisiting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner country,city;
    TextView expense,days,selectedDays,total;
    Button visit,remove;
    ListView selectedCities;
    SeekBar sb;

    String countryList[]={"Canada","France","India","Italy","Germany"};
    ArrayList<City>cityList=new ArrayList<>();
    ArrayList<City>tempCity=new ArrayList<>();

    ArrayList<SelectedCity>visitedCities=new ArrayList<>();
    ArrayList<String>names=new ArrayList<>();

    public static String theCity;
    public static double totalAmount;
    public static int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fillData();
        setContentView(R.layout.activity_main);
        country=findViewById(R.id.spCountry);
        city=findViewById(R.id.spCity);
        expense=findViewById(R.id.txvExpense);
        days=findViewById(R.id.txvDays);
        selectedDays=findViewById(R.id.txvSelDays);
        total=findViewById(R.id.txvTotal);
        visit=findViewById(R.id.btnVisit);
        remove=findViewById(R.id.btnRemove);
        selectedCities=findViewById(R.id.lvSelCities);
        sb=findViewById(R.id.sbDays);
        //filling the first spinner with country name by creating an array adapter of country
        ArrayAdapter aa=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,countryList);
        country.setAdapter(aa);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cityName[]=getCities(countryList[position]);
                ArrayAdapter aa2=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,cityName);
                city.setAdapter(aa2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                expense.setText(String.valueOf(tempCity.get(position).getExpense()));
                theCity=tempCity.get(position).getCity();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //ssekbar change
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                days.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //button visit: add in list of city selected and count total
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int noDays=Integer.parseInt(days.getText().toString());
                double dailyExpense=Double.parseDouble(expense.getText().toString());
                double amount=noDays*dailyExpense;
                visitedCities.add(new SelectedCity(theCity,noDays,amount));
                names.add(theCity);
                totalAmount+=amount;
                ArrayAdapter aa3=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,names);
                selectedCities.setAdapter(aa3);
                total.setText(String.valueOf(totalAmount));
            }
        });

        //list even to cleick selected city and show days
        selectedCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedDays.setText(String.valueOf(visitedCities.get(position).getDays()));
                index=position;
            }
        });

        //btn remove
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalAmount-=visitedCities.get(index).getTotal();
                names.remove(index);
                visitedCities.remove(index);

                ArrayAdapter aa3=new ArrayAdapter(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,names);
                selectedCities.setAdapter(aa3);
                total.setText(String.valueOf(totalAmount));

            }
        });
    }

    //filling data of city
    public void fillData(){
        cityList.add(new City(countryList[0],"Toronto",200));
        cityList.add(new City(countryList[0],"Montreal",150));
        cityList.add(new City(countryList[0],"Vancouver",300));
        cityList.add(new City(countryList[1],"Paris",100));
        cityList.add(new City(countryList[1],"Marseille",200));
        cityList.add(new City(countryList[2],"Delhi",50));
        cityList.add(new City(countryList[2],"Mumbai",60));
        cityList.add(new City(countryList[2],"Kolkata",30));
        cityList.add(new City(countryList[3],"Rome",110));
        cityList.add(new City(countryList[3],"Milan",220));
        cityList.add(new City(countryList[3],"Venice",330));
        cityList.add(new City(countryList[3],"Florence",440));
        cityList.add(new City(countryList[4],"Berlin",500));
        cityList.add(new City(countryList[4],"Hamburg",600));
        cityList.add(new City(countryList[4],"Munich",700));
    }

    //method receives country name and fill the tempCity and return array of city names
    public String[] getCities(String countryName){
        String cityNames[];
        tempCity.clear();
      for(City ct:cityList)
          if(ct.getCountry().equals(countryName))
              tempCity.add(ct);
          cityNames=new String[tempCity.size()];
          for(int i=0;i<tempCity.size();i++)
              cityNames[i]=tempCity.get(i).getCity();
          return cityNames;
    }

}