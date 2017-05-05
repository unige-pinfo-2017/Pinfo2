import { Device } from './device';
import { Workstation } from "../workstation";
import { DeviceType } from "./deviceType";

export const DEVICES: Device[] = [
    {
        id: 11,
        assignedWs: new Workstation(1),
        typeMachine: DeviceType.Socket
    },
    {
        id: 12,
        assignedWs: new Workstation(1),
        typeMachine: DeviceType.Ampoule
    },
    {
        id: 13,
        assignedWs: new Workstation(1),
        typeMachine: DeviceType.Aquarium
    },
    {
        id: 14,
        assignedWs: new Workstation(1),
        typeMachine: DeviceType.Ampoule
    }
];
