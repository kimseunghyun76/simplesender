<?xml version="1.0" encoding="UTF-8"?>
<SV20_ShipData:DataSet xmlns:SV20_ShipData="http://www.iho.int/SV20_ShipData/gml/1.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"	
	xmlns:gml="http://www.opengis.net/gml/3.2"	
	xmlns:S100="http://www.iho.int/s100gml/1.0"	
	xmlns:xlink="http://www.w3.org/1999/xlink" 	gml:id="SV20_ShipData,1.2">
	<gml:boundedBy>	
		<gml:Envelope srsName="EPSG:4326">
		<gml:lowerCorner>0.000000 0.000000</gml:lowerCorner>			
		<gml:upperCorner>0.000000 0.000000</gml:upperCorner>	
		</gml:Envelope>
	</gml:boundedBy>
	<DatasetIdentificationInformation>
		<S100:encodingSpecification>S-100</S100:encodingSpecification>
		<S100:encodingSpecificationEdition>1.2</S100:encodingSpecificationEdition>		
		<S100:productIdentifier>S-100</S100:productIdentifier>		
		<S100:productEdition>SV20</S100:productEdition>		
		<S100:applicationProfile>e-Navi</S100:applicationProfile>	
		<S100:datasetFileIdentifier>e-Navi</S100:datasetFileIdentifier>	
		<S100:datasetTitle>Sample dataset for SV20</S100:datasetTitle>	
		<S100:datasetReferenceDate>2017-12-04</S100:datasetReferenceDate>	
		<S100:datasetLanguage>en</S100:datasetLanguage>		
		<S100:datasetAbstract>e-Navi</S100:datasetAbstract>		
		<S100:datasetTopicCategory>e-Navi</S100:datasetTopicCategory>	
	</DatasetIdentificationInformation>	
	<imember>	
		<SV20_ShipData:ShipData gml:id="IM.0001">	
			<navigationDeckAlarmTransmit gml:id="a.0001" xlink:href="#IM.0002" xlink:role="consists"/>		
			<engineRoomAlarmTransmit gml:id="a.0003" xlink:href="#IM.0003" xlink:role="consists"/>		
			<engineRoomAlarmTransmit gml:id="a.0005" xlink:href="#IM.0004" xlink:role="consists"/>	
			<engineRoomAlarmTransmit gml:id="a.0007" xlink:href="#IM.0005" xlink:role="consists"/>	
			<engineRoomAlarmTransmit gml:id="a.0009" xlink:href="#IM.0006" xlink:role="consists"/>	
			<engineRoomAlarmTransmit gml:id="a.0011" xlink:href="#IM.0007" xlink:role="consists"/>	
			<engineRoomAlarmTransmit gml:id="a.0013" xlink:href="#IM.0008" xlink:role="consists"/>
			<engineRoomAlarmTransmit gml:id="a.0015" xlink:href="#IM.0009" xlink:role="consists"/>	
			<fireAlarmTransmit gml:id="a.0017" xlink:href="#IM.0010" xlink:role="consists"/>		
			<fireAlarmTransmit gml:id="a.0019" xlink:href="#IM.0011" xlink:role="consists"/>	
			<recoveryInfoTransmit gml:id="a.0021" xlink:href="#IM.0012" xlink:role="consists"/>		
		</SV20_ShipData:ShipData>	
	</imember>
	<imember>	
		<SV20_ShipData:NavigationEquipmentPowerFail gml:id="IM.0002">	
			<navigationDeckAlarmTransmit gml:id="a.0002" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<log>false</log>
			<ais>false</ais>	
			<firGps>false</firGps>		
			<secGps>false</secGps>	
			<ecdis>false</ecdis>	
			<firRadar>true</firRadar>
			<secRadar>true</secRadar>	
			<echoSounder>false</echoSounder>		
			<gyroCompass>false</gyroCompass>		
			<autoPilot>false</autoPilot>	
		</SV20_ShipData:NavigationEquipmentPowerFail>	
	</imember>	
	<imember>	
		<SV20_ShipData:AMSMainEngineActivation gml:id="IM.0003">	
			<engineRoomAlarmTransmit gml:id="a.0004" xlink:href="#IM.0001" xlink:role="consistsOf"/>
			<shutDown>false</shutDown>			
			<slowDown>false</slowDown>	
			</SV20_ShipData:AMSMainEngineActivation>	
			</imember>
			<imember>	
			<SV20_ShipData:AMSGenerator gml:id="IM.0004">	
			<engineRoomAlarmTransmit gml:id="a.0006" xlink:href="#IM.0001" xlink:role="consistsOf"/>		
			<identification>AMS.GE.1</identification>		
			<cfwHighTempTrip>false</cfwHighTempTrip>	
			<loLowPressTrip>false</loLowPressTrip>	
			<overSpeedTrip>false</overSpeedTrip>	
			<commonTrip>false</commonTrip>		
			<speedSensorFailTrip>false</speedSensorFailTrip>
			<startFail>false</startFail>	
			<commonShutDown>false</commonShutDown>	
		</SV20_ShipData:AMSGenerator>
	</imember>
	<imember>	
		<SV20_ShipData:AMSGenerator gml:id="IM.0005">		
			<engineRoomAlarmTransmit gml:id="a.0008" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<identification>AMS.GE.2</identification>		
			<cfwHighTempTrip>false</cfwHighTempTrip>	
			<loLowPressTrip>false</loLowPressTrip>		
			<overSpeedTrip>false</overSpeedTrip>		
			<commonTrip>false</commonTrip>		
			<speedSensorFailTrip>false</speedSensorFailTrip>	
			<startFail>false</startFail>		
			<commonShutDown>false</commonShutDown>	
		</SV20_ShipData:AMSGenerator>
	</imember>
	<imember>	
		<SV20_ShipData:AMSGenerator gml:id="IM.0006">	
			<engineRoomAlarmTransmit gml:id="a.0010" xlink:href="#IM.0001" xlink:role="consistsOf"/>
			<identification>AMS.GE.3</identification>	
			<cfwHighTempTrip>false</cfwHighTempTrip>	
			<loLowPressTrip>false</loLowPressTrip>		
			<overSpeedTrip>false</overSpeedTrip>		
			<commonTrip>false</commonTrip>	
			<speedSensorFailTrip>false</speedSensorFailTrip>		
			<startFail>false</startFail>		
			<commonShutDown>false</commonShutDown>	
		</SV20_ShipData:AMSGenerator>	
	</imember>
	<imember>
		<SV20_ShipData:AMSEmergencyGenerator gml:id="IM.0007">	
			<engineRoomAlarmTransmit gml:id="a.0012" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<trip>false</trip>		
			<overSpeedTrip>false</overSpeedTrip>	
			<run>false</run>	
			<preperentialTrip>false</preperentialTrip>	
		</SV20_ShipData:AMSEmergencyGenerator>
	</imember>
	<imember>	
		<SV20_ShipData:AMSSteeringGear gml:id="IM.0008">	
			<engineRoomAlarmTransmit gml:id="a.0014" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<identification>AMS.SG.1</identification>		
			<controlPowerFail>false</controlPowerFail>		
			<hydLOTkLL>false</hydLOTkLL>	
			<hydLockAlarm>false</hydLockAlarm>	
			<motorRun>false</motorRun>		
			<noVolt>false</noVolt>			
			<overLoad>false</overLoad>	
			<phaseFail>false</phaseFail>
		</SV20_ShipData:AMSSteeringGear>	
	</imember>	
	<imember>
		<SV20_ShipData:AMSSteeringGear gml:id="IM.0009">	
			<engineRoomAlarmTransmit gml:id="a.0016" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<identification>AMS.SG.2</identification>	
			<controlPowerFail>false</controlPowerFail>	
			<hydLOTkLL>false</hydLOTkLL>	
			<hydLockAlarm>false</hydLockAlarm>		
			<motorRun>false</motorRun>		
			<noVolt>false</noVolt>		
			<overLoad>false</overLoad>	
			<phaseFail>false</phaseFail>	
		</SV20_ShipData:AMSSteeringGear>	
	</imember>
	<imember>
		<SV20_ShipData:FireDoorInfo gml:id="IM.0010">	
			<fireAlarmTransmit gml:id="a.0018" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<fireDoorOpen>true</fireDoorOpen>	
			<fireDoorPositionId>0</fireDoorPositionId>	
		</SV20_ShipData:FireDoorInfo>
	</imember>	
	<imember>	
		<SV20_ShipData:FireAlarm gml:id="IM.0011">		
			<fireAlarmTransmit gml:id="a.0020" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<detectorType>FS</detectorType>	
			<alarm>false</alarm>	
			<positionId>0</positionId>	
			<alarmTime>000000</alarmTime>	
		</SV20_ShipData:FireAlarm>
	</imember>	
	<imember>	
		<SV20_ShipData:RecoveryInfo gml:id="IM.0012">	
			<recoveryInfoTransmit gml:id="a.0022" xlink:href="#IM.0001" xlink:role="consistsOf"/>	
			<heeling>1.000000</heeling>		
			<rollingAngle>1.000000</rollingAngle>	
			<pitchingAngle>1.000000</pitchingAngle>		
			<gpsHeading>0.000000</gpsHeading>		
			<gpsSpeed>1.000000</gpsSpeed>	
			<windDirection>0.000000</windDirection>	
			<windSpeed>1.000000</windSpeed>		
			<freshWaterTank>1.000000</freshWaterTank>		
			<forepeakTank>1.000000</forepeakTank>			
			<waterBallastTank>1.000000</waterBallastTank>	
			<aftpeakTank>1.000000</aftpeakTank>		
			<oilyBilgeTank>1.000000</oilyBilgeTank>	
		</SV20_ShipData:RecoveryInfo>	
	</imember>
</SV20_ShipData:DataSet>