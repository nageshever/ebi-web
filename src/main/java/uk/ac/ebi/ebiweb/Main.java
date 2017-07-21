package uk.ac.ebi.ebiweb;

import org.springframework.stereotype.Component;
import uk.ac.ebi.ebiweb.Accession;
import uk.ac.ebi.ebiweb.AccessionRange;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@Component
public class Main {
	
	public String returnOrderedAccessionNumbers(String accessionNumbers) throws Exception {

		List<Accession> accessionList = new ArrayList<>();
		 StringBuffer buffer = new StringBuffer();
		 
		 if (accessionNumbers==null) {
			 throw new Exception("Access Number input is null");
		 }

        //Now have a list of sorted accessions sorted in a List of Accession objects.
        //By this time, we have a alphabet and number part split while creating Accession object
        List<String> sortedList = Arrays.stream(accessionNumbers.split(","))
                .sorted().collect(toList());//.forEach(sortedItem -> System.out.println(sortedItem));
        sortedList.stream().forEach(e -> {accessionList.add(new Accession(e));}); //Creating a list of Accession objects
        Map<String, List<AccessionRange>> rangeMapList = new HashMap<>();
        Map<String, List<Accession>> tokenMap = accessionList
                .stream()
                //From accessionList, creating a hashmap of token and list of numerics as value
                .collect(groupingBy(Accession::getAlphabetPart));
        //System.out.println("Size of token Map "+tokenMap.size());
        /*tokenMap.keySet().stream().forEach(e -> {System.out.println(e);
        });*/
        tokenMap.keySet().stream()
                .forEach(e -> { //Now Iterating the hashmap, for each keyset
                                    List<Accession> tempList = tokenMap.get(e);
                                    List<AccessionRange> rangeList = new ArrayList<>();
                                    for (int i=0, j=0;i<tempList.size();i=j,j++) {
                                        AccessionRange range = new AccessionRange(tempList.get(i).getAccessionNumber());
                                       j = i+1;
                                       if (j<tempList.size()) {
                                           while (j<tempList.size() && tempList.get(i).compareTo(tempList.get(j)) == 0) {

                                             //  System.out.println("j==>"+tempList.get(j).getAccessionNumber()+" == ");
                                               range.setEndAccession(tempList.get(j).getAccessionNumber());
                                                   i++;j++;
                                           }
                                       }
                                       rangeList.add(range);
                                   }
                                   rangeMapList.put(e, rangeList);
                   });
      /*  rangeMapList.keySet().forEach(e -> {
            rangeMapList.get(e).stream().forEach(ar -> System.out.println(ar));
        });*/
        List<String> keySortedList =  rangeMapList.keySet().stream().sorted().collect(toList());
       
        for (int i=0;i<keySortedList.size();i++) {
            List<AccessionRange> finalList = rangeMapList.get(keySortedList.get(i));
            for (int j=0;j<finalList.size();j++) {
                if (i==keySortedList.size()-1) {
                   // System.out.print(finalList.get(j).toString());
                	buffer.append(finalList.get(j).toString());
                } else {
                	buffer.append(finalList.get(j).toString()).append(", ");
                       // System.out.print(finalList.get(j).toString()+", ");
                }
            }

        }
        return buffer.toString();
	}

}
