package fr.micropole.javascript;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import fr.micropole.pojo.Category;

public class JavaEnJS {

    private static final String JAVASCRIPT_OPEN  = "<script type=\"text/javascript\">";
    private static final String JAVASCRIPT_CLOSE = "</script>";
    private static final char   CROCHET_OUVRANT  = '[';
    private static final char   CROCHET_FERMANT  = ']';
    private static final char   VIRGULE          = ',';
    private static final char   APOSTROPHE       = '\'';

    public static StringBuilder PieChart( HashMap<Category, Double> categorisation ) {

        StringBuilder pieChart = new StringBuilder();
        pieChart.append( JAVASCRIPT_OPEN );
        pieChart.append( "google.charts.load(\"current\", { packages : [ \"corechart\" ]});" );
        pieChart.append( "google.charts.setOnLoadCallback(drawChart);" );
        pieChart.append( "function drawChart() {" );
        pieChart.append( "var data = google.visualization.arrayToDataTable([" );
        pieChart.append( "[ 'Categorie', 'Montant par categorie' ], " );
        Set<Category> cles = categorisation.keySet();
        Iterator<Category> it = cles.iterator();
        while ( it.hasNext() ) {
            Category cat = it.next();
            pieChart.append( CROCHET_OUVRANT );
            pieChart.append( APOSTROPHE );
            pieChart.append( cat.getName() );
            pieChart.append( APOSTROPHE );
            pieChart.append( VIRGULE );
            pieChart.append( categorisation.get( cat ) );
            pieChart.append( CROCHET_FERMANT );
            if ( it.hasNext() ) {
                pieChart.append( VIRGULE );
            }
        }

        pieChart.append( "]);" );
        pieChart.append( "var options = {" );
        pieChart.append( "title : 'Camembert des dépenses'," );
        pieChart.append( "is3D : true," );
        pieChart.append( "};" );
        pieChart.append( "var chart = new google.visualization.PieChart(document" );
        pieChart.append( ".getElementById('piechart_3d'));" );
        pieChart.append( "chart.draw(data, options);" );
        pieChart.append( "}" );

        pieChart.append( JAVASCRIPT_CLOSE );

        return pieChart;
    }
}
