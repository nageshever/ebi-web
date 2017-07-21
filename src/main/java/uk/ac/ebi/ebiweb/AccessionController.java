package uk.ac.ebi.ebiweb;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccessionController {
    private final org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Main main;
    @RequestMapping(value = "/",
            method = RequestMethod.GET
          //  consumes = MediaType.TEXT_PLAIN_VALUE,
         //   produces = MediaType.TEXT_PLAIN_VALUE
            )
    public @ResponseBody String orderAccessions(@RequestParam("accession") String accessionNumbers) {
        String returnAccessionRanges = "";
        try {
            if (accessionNumbers.length() == 0 ) {
                log.error("One comma seperated list Accession Numbers must be passed through command prompt ");
                return "One comma seperated list Accession Numbers must be passed through command prompt ";
            }
            returnAccessionRanges = main.returnOrderedAccessionNumbers(accessionNumbers);
        } catch (Exception e) {
            log.error(e.getMessage());
            return "Exception Occurred";
        }

    return returnAccessionRanges;
    }
}
