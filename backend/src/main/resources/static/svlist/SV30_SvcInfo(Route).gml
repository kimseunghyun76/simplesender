<?xml version="1.0" encoding="UTF-8"?>
	<SV30_Route:DataSet xmlns:SV30_Route="http://www.iho.int/SV30_Route/gml/1.0"	
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"	
	xmlns:gml="http://www.opengis.net/gml/3.2"
	xmlns:S100="http://www.iho.int/s100gml/1.0"
	xmlns:xlink="http://www.w3.org/1999/xlink" gml:id="SV30_Route,1.2">	
	<gml:boundedBy>	
		<gml:Envelope srsName="EPSG:4326">	
			<gml:lowerCorner>1.100000 1.200000</gml:lowerCorner>	
			<gml:upperCorner>3.100000 3.200000</gml:upperCorner>
		</gml:Envelope>
	</gml:boundedBy>	
	<DatasetIdentificationInformation>	
	<S100:encodingSpecification>S-100</S100:encodingSpecification>	
	<S100:encodingSpecificationEdition>1.2</S100:encodingSpecificationEdition>	
	<S100:productIdentifier>S-100</S100:productIdentifier>	
	<S100:productEdition>SV30</S100:productEdition>	
	<S100:applicationProfile>e-Navi</S100:applicationProfile>
	<S100:datasetFileIdentifier>e-Navi</S100:datasetFileIdentifier>	
	<S100:datasetTitle>Sample dataset for SV30</S100:datasetTitle>	
	<S100:datasetReferenceDate>2017-11-22</S100:datasetReferenceDate>
	<S100:datasetLanguage>en</S100:datasetLanguage>
	<S100:datasetAbstract>e-Navi</S100:datasetAbstract>	
	<S100:datasetTopicCategory>e-Navi</S100:datasetTopicCategory>	
	</DatasetIdentificationInformation>	
	<imember>	
		<SV30_Route:RouteInfo gml:id="IM.0001">	
			<issueDate>issueDate</issueDate>	
			<vvd>Voyage No</vvd>			
			<destinationDtg>5.100000</destinationDtg>	
			<destinationTtg>destinationTtg</destinationTtg>
			<eta>eta</eta>	
		</SV30_Route:RouteInfo>	
	</imember>
	<imember>
		<SV30_Route:ShipSpec gml:id="IM.0002">	
			<shipName>shipName2</shipName>	
			<shipId>shipId</shipId>		
			<mmsi>mmsi2</mmsi>		
			<imoNo>imoNo2</imoNo>	
			<callSign>callSign2</callSign>	
			<netTonnage>4.000000</netTonnage>
			<grossTonnage>5.000000</grossTonnage>	
			<length>4.100000</length>		
			<breadth>3.100000</breadth>	
			<depth>5.100000</depth>		
			<vsslTp>InternationalCarFerry</vsslTp>	
			<hullTp>hullTp</hullTp>	
			<shipSvrType>Routine</shipSvrType>	
			<inOutPortType>InnerPort</inOutPortType>	
			<buildDate>20170701</buildDate>	
			<ownerNationality>ownerNationality</ownerNationality>		
			<owner>owner</owner>	
			<shipOperator>shipOperator</shipOperator>	
			<operatorFlag>operatorFlag</operatorFlag>	
			<previousCallsign>previousCallsign</previousCallsign>	
			<vesselFlag>vesselFlag</vesselFlag>	
			<mainEngHp>1.100000</mainEngHp>	
			<regstPort>regstPort</regstPort>
		</SV30_Route:ShipSpec>	
	</imember>
	<imember>	
		<SV30_Route:ShipCondi gml:id="IM.0003">		
			<displacement>3.100000</displacement>	
			<draftFwd>5.100000</draftFwd>	
			<draftAft>4.100000</draftAft>	
		</SV30_Route:ShipCondi>	
	</imember>
	<member>	
		<SV30_Route:Route gml:id="M.0001">		
			<routeService gml:id="a.0001" xlink:href="#M.0002" xlink:role="use"/>	
			<routeService gml:id="a.0005" xlink:href="#M.0004" xlink:role="use"/>
			<routeService gml:id="a.0009" xlink:href="#M.0006" xlink:role="use"/>	
			<routeAdditionalInformation gml:id="a.0013" xlink:href="#IM.0001" xlink:role="role"/>	
			<shipInformation gml:id="a.0014" xlink:href="#IM.0002" xlink:role="role"/>		
			<shipInformation gml:id="a.0015" xlink:href="#IM.0003" xlink:role="role"/>	
			<startLat>33.576228</startLat>	
			<startLong>126.594904</startLong>	
			<endLat>35.156410</endLat>		
			<endLong>129.137616</endLong>	
			<waypointCount>3</waypointCount>
			<routeType>LicenseRoute</routeType>	
			<geometry>	
				<S100:pointProperty>	
					<S100:Point gml:id="PT.0001">		
						<gml:pos>33.576228 126.594904</gml:pos>		
					</S100:Point>	
				</S100:pointProperty>	
			</geometry>	
		</SV30_Route:Route>
	</member>	
	<member>	
		<SV30_Route:Waypoint gml:id="M.0002">	
			<routeService gml:id="a.0002" xlink:href="#M.0001" xlink:role="usedby"/>	
			<routeLine gml:id="a.0003" xlink:href="#M.0003" xlink:role="consistsOf"/>		
			<waypointNo>1</waypointNo>		
			<nextWpDtg>2.200000</nextWpDtg>		
			<nextWpTtg>timeNextWpTtg1</nextWpTtg>		
			<wpCategory>1</wpCategory>		
			<geometry>		
				<S100:pointProperty>			
					<S100:Point gml:id="PT.0002">					
						<gml:pos>1.100000 1.200000</gml:pos>	
					</S100:Point>	
				</S100:pointProperty>	
			</geometry>	
		</SV30_Route:Waypoint>
	</member>
	<member>		
		<SV30_Route:Leg gml:id="M.0003">	
			<routeLine gml:id="a.0004" xlink:href="#M.0002" xlink:role="consists"/>		
			<plannedCourse>1</plannedCourse>
			<plannedSpeed>2.200000</plannedSpeed>	
			<geometry>		
				<S100:curveProperty>		
					<S100:Curve gml:id="C.0001">			
						<gml:segments>					
							<gml:LineStringSegment>						
								<gml:posList>1.100000 1.200000 2.100000 2.200000</gml:posList>		
							</gml:LineStringSegment>	
						</gml:segments>		
					</S100:Curve>			
				</S100:curveProperty>		
			</geometry>		
		</SV30_Route:Leg>
	</member>
	<member>	
		<SV30_Route:Waypoint gml:id="M.0004">		
			<routeService gml:id="a.0006" xlink:href="#M.0001" xlink:role="usedby"/>	
			<routeLine gml:id="a.0007" xlink:href="#M.0005" xlink:role="consistsOf"/>	
			<waypointNo>2</waypointNo>		
			<nextWpDtg>2.200000</nextWpDtg>		
			<nextWpTtg>timeNextWpTtg2</nextWpTtg>	
			<wpCategory>3</wpCategory>	
			<geometry>		
				<S100:pointProperty>	
					<S100:Point gml:id="PT.0003">		
						<gml:pos>2.100000 2.200000</gml:pos>	
					</S100:Point>			
				</S100:pointProperty>	
			</geometry>	
		</SV30_Route:Waypoint>	
	</member>
	<member>	
		<SV30_Route:Leg gml:id="M.0005">		
			<routeLine gml:id="a.0008" xlink:href="#M.0004" xlink:role="consists"/>		
			<plannedCourse>1</plannedCourse>	
			<plannedSpeed>2.200000</plannedSpeed>	
			<geometry>		
				<S100:curveProperty>	
					<S100:Curve gml:id="C.0002">	
						<gml:segments>				
							<gml:LineStringSegment>		
								<gml:posList>2.100000 2.200000 3.100000 3.200000</gml:posList>		
							</gml:LineStringSegment>			
						</gml:segments>		
					</S100:Curve>		
				</S100:curveProperty>		
			</geometry>	
		</SV30_Route:Leg>	
	</member>	
	<member>	
		<SV30_Route:Waypoint gml:id="M.0006">
			<routeService gml:id="a.0010" xlink:href="#M.0001" xlink:role="usedby"/>		
			<routeLine gml:id="a.0011" xlink:href="#M.0007" xlink:role="consistsOf"/>	
			<waypointNo>3</waypointNo>		
			<nextWpDtg>2.200000</nextWpDtg>
			<nextWpTtg>timeNextWpTtg3</nextWpTtg>	
			<wpCategory>2</wpCategory>	
			<geometry>		
				<S100:pointProperty>		
					<S100:Point gml:id="PT.0004">				
						<gml:pos>3.100000 3.200000</gml:pos>		
					</S100:Point>		
				</S100:pointProperty>
			</geometry>	
		</SV30_Route:Waypoint>
	</member>	
	<member>	
		<SV30_Route:Leg gml:id="M.0007">			
			<routeLine gml:id="a.0012" xlink:href="#M.0006" xlink:role="consists"/>
		</SV30_Route:Leg>	
	</member>
</SV30_Route:DataSet>