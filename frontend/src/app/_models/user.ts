import { Device } from './device';
export class User {
    id: number;
    username: string;
    password: string;
    firstName: string;
    lastName: string;
    devices: Device[];
}