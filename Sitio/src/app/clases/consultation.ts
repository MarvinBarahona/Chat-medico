import { Doctor, Patient, Diagnostic, Prescription, Reference } from './';

export class Consultation{
  id: number;
  date: Date;
  doctor: Doctor;
  patient: Patient;
  diagnostic: Diagnostic;
  prescription: Prescription;
  reference: Reference;
}
