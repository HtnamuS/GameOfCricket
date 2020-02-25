package htnamus.goc.DTO;

import htnamus.goc.model.Innings;
import htnamus.goc.model.Partnership;

import java.util.List;

public class PartnershipsReport {
	public final List<Partnership.PartnershipReport> firstInningsPartnershipsReport,
							secondInningsPartnershipsReport;
	
	public PartnershipsReport(Innings firstInnings, Innings secondInnings) {
		this.firstInningsPartnershipsReport = firstInnings.getPartnershipsReport();
		this.secondInningsPartnershipsReport = secondInnings.getPartnershipsReport();
	}
}