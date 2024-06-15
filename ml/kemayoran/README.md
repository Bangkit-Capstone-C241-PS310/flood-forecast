# Kemayoran Model

## Description
Model ini mewakili Stasiun BMKG Kemayoran yang mengcover 100 kelurahan pada 2 kota, yaitu Jakarta Pusat & Jakarta Barat.</br>
[*File Model: kemayoran.zip*](https://drive.google.com/file/d/16iQpouWx_214ixmx438YYqyvPIiHcPI9)


## Model Architecture
![image](https://github.com/Bangkit-Capstone-C241-PS310/flood-forecast/assets/99129519/74331f71-e9b8-4f9c-82ab-86f885f92b04)
- **Y1_output**: Outputs Banjir predictions.
- **Y2_output**: Outputs Iklim predictions.

## Dataset
Model ini membutuhkan input berupa:</br>
  - rataan_curah_hujan
  - rataan_suhu
  - rataan_kecepatan_angin
  - rataan_kelembapan
    
[*File Input: Kemayoran.xlsx*](https://docs.google.com/spreadsheets/d/1knZoFp26nnfgEsRhnXqpkkOcJlG38Wxi)

## Output Y1 (Predict Banjir)
Output Y1 secara berurutan:
  1. Cempaka Putih Barat
  2. Cempaka Putih Timur
  3. Rawasari
  4. Cideng
  5. Duri Pulo
  6. Gambir
  7. Kebon Kelapa
  8. Petojo Selatan
  9. Petojo Utara
  10. Galur
  11. Johar Baru
  12. Kampung Rawa
  13. Tanah Tinggi
  14. Cempaka Baru
  15. Gunung Sahari Selatan
  16. Harapan Mulya
  17. Kebon Kosong
  18. Kemayoran
  19. Serdang
  20. Sumur Batu
  21. Utan Panjang
  22. Cikini
  23. Gondangdia
  24. Kebon Sirih
  25. Menteng
  26. Pegangsaan
  27. Gunung Sahari Utara
  28. Karang Anyar
  29. Kartini
  30. Mangga Dua Selatan
  31. Pasar Baru
  32. Bungur
  33. Kenari
  34. Kramat
  35. Kwitang
  36. Paseban
  37. Senen
  38. Bendungan Hilir
  39. Gelora
  40. Kampung Bali
  41. Karet Tengsin
  42. Kebon Kacang
  43. Kebon Melati
  44. Petamburan
  45. Cengkareng Barat
  46. Cengkareng Timur
  47. Duri Kosambi
  48. Kapuk
  49. Kedaung Kali Angke
  50. Rawa Buaya
  51. Grogol
  52. Jelambar
  53. Jelambar Baru
  54. Tanjung Duren Selatan
  55. Tanjung Duren Utara
  56. Tomang
  57. Wijaya Kusuma
  58. Kalideres
  59. Kamal
  60. Pegadungan
  61. Semanan
  62. Tegal Alur
  63. Duri Kepa
  64. Kebon Jeruk
  65. Kedoya Selatan
  66. Kedoya Utara
  67. Kelapa Dua
  68. Sukabumi Selatan
  69. Sukabumi Utara
  70. Joglo
  71. Kembangan Selatan
  72. Kembangan Utara
  73. Meruya Selatan
  74. Meruya Utara
  75. Srengseng
  76. Jatipulo
  77. Kemanggisan
  78. Kota Bambu Selatan
  79. Kota Bambu Utara
  80. Palmerah
  81. Slipi
  82. Glodok
  83. Keagungan
  84. Krukut
  85. Mangga Besar
  86. Maphar
  87. Pinangsia
  88. Taman Sari
  89. Tangki
  90. Angke
  91. Duri Selatan
  92. Duri Utara
  93. Jembatan Besi
  94. Jembatan Lima
  95. Kali Anyar
  96. Krendang
  97. Pekojan
  98. Roa Malaka
  99. Tambora
  100. Tanah Sereal

*Threshold Y1 0.9999*

0: Tidak Banjir

1: Banjir

## Output Y2 (Predict Iklim)

Output Y2 secara berurutan:
  1. rataan_curah_hujan
  2. rataan_suhu
  3. rataan_kecepatan_angin
  4. rataan_kelembapan
