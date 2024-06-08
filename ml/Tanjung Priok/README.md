# Tanjung Priok Stasiun Model
This model predicts whether tomorrow will be a flood or not and also predicts climate indicators such as rainfall, temperature, windspeed, and humidity using climate indicators index from the past 60 days. <br>

**Note**: The model only predicted for the next day

## Model Input
This model expected a 3-dimensional array as an input. The shape detail is (1, 60, 4) where 1 is the batch size, 60 is the time window size, and 4 is the number of features in an ordered sequence ([rainfall, temperature, windspeed, and humidity]). 

### Input example
input = [[[12.3, 27.1,  4. , 84. ],
          [ 0. , 27.5,  4. , 82. ],
          [ 6.4, 28.8,  4. , 80. ],

          ...,
          [ 0. , 27.9,  3. , 77. ],
          [ 2.5, 26. ,  4. , 89. ],
          [76. , 25.5,  2. , 88. ]]]

## Model Architecture
![Priok Model Architecture](https://github.com/Bangkit-Capstone-C241-PS310/flood-forecast/assets/134376120/298aad06-f3b5-4b20-afec-0bd949753ac7)

## Model Output
Model predicted output will be an array where the first index is flood prediction and the second index will be climate indicators prediction. 

### Code Example

```python
flood_prediction, climate_prediction = model.predict(input)
```
The climate shape will have the same order as the input, such as [1412, 241, 0.1231, 4]

the flood prediction will return an array like this
[[1, 0 , 0.999, ..., 0.2]]

Each index corresponds to this ordered kelurahan list. the list are: <br>
Ancol <br>
Cilincing <br>
Kalibaru <br>
Kamal Muara <br>
Kapuk Muara <br>
Kebon Bawang <br>
Kelapa Gading Barat <br>
Kelapa Gading Timur <br>
Koja <br>
Lagoa <br>
Marunda <br>
Pademangan Barat <br>
Pademangan Timur <br>
Papanggo <br>
Pegangsaan Dua <br>
Pejagalan <br>
Penjaringan <br>
Pluit <br>
Rawa Badak Selatan <br>
Rawa Badak Utara <br>
Rorotan <br>
Semper Barat <br>
Semper Timur <br>
Sukapura <br>
Sungai Bambu <br>
Sunter Agung <br>
Sunter Jaya <br>
Tanjung Priok <br>
Tugu Selatan <br>
Tugu Utara <br>
Warakas <br>

# Thank you
