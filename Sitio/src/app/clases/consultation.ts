import { Doctor, Patient, Diagnostic, Prescription, Reference } from './';

export class Consultation{
  id: number;
  doctor: Doctor;
  patient: Patient;
  diagnostic: Diagnostic;
  prescription: Prescription;
  reference: Reference; 
}
