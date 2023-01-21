package at.fhhagenberg.sqe.model;

import sqelevator.IElevator;
import at.fhhagenberg.sqe.mockobjects.IElevatorMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;


import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

public class RMIElevatorServiceTest {

    private RMIElevatorService RmiElevatorService;
    private IElevatorMock elevatorMock;

    private IElevatorMock elevatorMockException;

    @BeforeEach
    void Setup() {
        elevatorMock = new IElevatorMock(3,8);
        RmiElevatorService = new RMIElevatorService(elevatorMock);

        elevatorMockException = Mockito.spy(new IElevatorMock(3,8));
    }


    @Test
    void TestCommittedDirection(){
        RmiElevatorService.setCommittedDirection(2,1);
        assertEquals(1, RmiElevatorService.getCommittedDirection(2));
    }

    @Test
    void TestElevatorAccel(){
        elevatorMock.setElevatorAccel(2,10);

        assertEquals(10, RmiElevatorService.getElevatorAccel(2));
    }

    @Test
    void TestElevatorButton(){
        elevatorMock.setElevatorButton(2,3,true);

        assertTrue(RmiElevatorService.getElevatorButton(2,3));
    }

    @Test
    void TestElevatorDoorStatus(){
        elevatorMock.setElevatorDoorStatus(2,2);

        assertEquals(2, RmiElevatorService.getElevatorDoorStatus(2));
    }

    @Test
    void TestElevatorFloor(){
        elevatorMock.setElevatorFloor(2,7);

        assertEquals(7, RmiElevatorService.getElevatorFloor(2));
    }

    @Test
    void TestElevatorNum(){
        elevatorMock.setElevatorNum(15);

        assertEquals(15, RmiElevatorService.getElevatorNum());
    }

    @Test
    void TestElevatorPosition(){
        elevatorMock.setElevatorPosition(2, 11);

        assertEquals(11, RmiElevatorService.getElevatorPosition(2));
    }

    @Test
    void TestElevatorSpeed(){
        elevatorMock.setElevatorSpeed(1, 42);

        assertEquals(42, RmiElevatorService.getElevatorSpeed(1));
    }

    @Test
    void TestElevatorWeight() {
        elevatorMock.setElevatorWeight(2, 666);

        assertEquals(666, RmiElevatorService.getElevatorWeight(2));
    }

    @Test
    void TestElevatorCapacity() {
        elevatorMock.setElevatorCapacity(0, 5);

        assertEquals(5, RmiElevatorService.getElevatorCapacity(0));
    }

    @Test
    void TestFloorButtonDown(){
        elevatorMock.setFloorButtonDown(7,true);

        assertTrue(RmiElevatorService.getFloorButtonDown(7));
    }

    @Test
    void TestFloorButtonUp(){
        elevatorMock.setFloorButtonUp(0, true);

        assertTrue(RmiElevatorService.getFloorButtonUp(0));
    }

    @Test
    void TestFloorHeight(){
        elevatorMock.setFloorHeight(369);

        assertEquals(369, RmiElevatorService.getFloorHeight());
    }

    @Test
    void TestFloorNum(){
        elevatorMock.setFloorNum(49);

        assertEquals(49, RmiElevatorService.getFloorNum());
    }

    @Test
    void TestServicesFloors() {
        RmiElevatorService.setServicesFloors(2,0, true);
        assertTrue(RmiElevatorService.getServicesFloors(2,0));
    }

    @Test
    void TestTarget(){
        RmiElevatorService.setTarget(0,7);
        assertEquals(7, RmiElevatorService.getTarget(0));
    }

    @Test
    void TestGetClockTick(){
        elevatorMock.setClockTick(299792458);

        assertEquals(299792458, RmiElevatorService.getClockTick());
    }



    @Test
    void TestCommittedDirection_Exception() throws RemoteException {
        elevatorMockException.setCommittedDirection(1,1);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getCommittedDirection(1);

        assertEquals(IElevator.ELEVATOR_DIRECTION_UNCOMMITTED, RmiElevatorServiceException.getCommittedDirection(1));


    }
    @Test
    void TestElevatorAccel_Exception() throws RemoteException {
        elevatorMockException.setElevatorAccel(1,10);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorAccel(1);

        assertEquals(0, RmiElevatorServiceException.getElevatorAccel(1));
    }
    @Test
    void TestElevatorButton_Exception() throws RemoteException {
        elevatorMockException.setElevatorButton(2,3,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorButton(2, 3);

        assertFalse(RmiElevatorServiceException.getElevatorButton(2,3));
    }

    @Test
    void TestElevatorDoorStatus_Exception() throws RemoteException {
        elevatorMockException.setElevatorDoorStatus(2,2);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorDoorStatus(2);

        assertEquals(0, RmiElevatorServiceException.getElevatorDoorStatus(2));
    }
    @Test
    void TestElevatorFloor_Exception() throws RemoteException {
        elevatorMockException.setElevatorFloor(0,7);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorFloor(0);

        assertEquals(0, RmiElevatorServiceException.getElevatorFloor(0));
    }
    @Test
    void TestElevatorNum_Exception() throws RemoteException {
        elevatorMockException.setFloorNum(15);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorNum();

        assertEquals(0, RmiElevatorServiceException.getElevatorNum());
    }
    @Test
    void TestElevatorPosition_Exception() throws RemoteException {
        elevatorMockException.setElevatorPosition(2,3);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorPosition(2);

        assertEquals(0, RmiElevatorServiceException.getElevatorPosition(2));
    }
    @Test
    void TestElevatorSpeed_Exception() throws RemoteException {
        elevatorMockException.setElevatorSpeed(0,42);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorSpeed(0);

        assertEquals(0, RmiElevatorServiceException.getElevatorSpeed(0));
    }
    @Test
    void TestElevatorWeight_Exception() throws RemoteException {
        elevatorMockException.setElevatorWeight(2,666);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorWeight(2);

        assertEquals(0, RmiElevatorServiceException.getElevatorWeight(2));
    }
    @Test
    void TestElevatorCapacity_Exception() throws RemoteException {
        elevatorMockException.setElevatorCapacity(1,6);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getElevatorCapacity(1);

        assertEquals(0, RmiElevatorServiceException.getElevatorCapacity(1));
    }
    @Test
    void TestFloorButtonDown_Exception() throws RemoteException {
        elevatorMockException.setFloorButtonDown(2,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorButtonDown(2);

        assertFalse(RmiElevatorServiceException.getFloorButtonDown(2));
    }
    @Test
    void TestFloorButtonUp_Exception() throws RemoteException {
        elevatorMockException.setFloorButtonUp(2,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorButtonUp(2);

        assertFalse(RmiElevatorServiceException.getFloorButtonUp(2));
    }
    @Test
    void TestFloorHeight_Exception() throws RemoteException {
        elevatorMockException.setFloorHeight(369);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorHeight();

        assertEquals(0, RmiElevatorServiceException.getFloorHeight());
    }
    @Test
    void TestFloorNum_Exception() throws RemoteException {
        elevatorMockException.setFloorNum(60);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getFloorNum();

        assertEquals(0, RmiElevatorServiceException.getFloorNum());
    }
    @Test
    void TestServicesFloors_Exception() throws RemoteException {
        elevatorMockException.setServicesFloors(2,5,true);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getServicesFloors(2,5);

        assertFalse(RmiElevatorServiceException.getServicesFloors(2, 5));
    }
    @Test
    void TestTarget_Exception() throws RemoteException {
        elevatorMockException.setTarget(1,5);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getTarget(1);

        assertEquals(0, RmiElevatorServiceException.getTarget(1));
    }
    @Test
    void TestClockTick_Exception() throws RemoteException {
        elevatorMockException.setClockTick(662607015);
        RMIElevatorService RmiElevatorServiceException = new RMIElevatorService(elevatorMockException);
        Mockito.doThrow(new RemoteException()).when(elevatorMockException).getClockTick();

        assertEquals(0, RmiElevatorServiceException.getClockTick());
    }



}
