package pro.verron.datamunging;

record WeatherRow(
        int day,
        double maxTemp,
        double minTemp
) {
    public double tempRange() {
        return maxTemp - minTemp;
    }
}
